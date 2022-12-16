package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.dto.UserAuthenticatedDto;
import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.security.UserPrincipal;
import cu.edu.cujae.pweb.service.AuthService;
import cu.edu.cujae.pweb.service.UserService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class UserBean {

    private UserDto logged_user;
    private UserDto logged_user_values;

    private String username = "";
    private String fullname = "";
    private String password = "";
    private String email = "";
    private String identification = "";

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    public UserBean() {
        // TODO Auto-generated constructor stub
    }

    public String registerNewUser() {
//        List<RoleDto> roles = new ArrayList<>();
//        roles.add(new RoleDto(2L, "", ""));
//		userService.createUser(new UserDto(null, username, "Example Name", password, email, "00000000000", roles));
        userService.registerUser(new UserDto(null, username, fullname, password, email, identification, null));
        return login();
    }

    public void editLoggedUser() {
        logged_user_values = userService.getUserById(logged_user.getId());
        logged_user_values.setPassword("");
//		logged_user_values = new UserDto();
//		logged_user_values.setId(logged_user.getId());
//		logged_user_values.setUsername(logged_user.getUsername());
//		logged_user_values.setFullName(logged_user.getFullName());
//		logged_user_values.setIdentification(logged_user.getIdentification());
//		logged_user_values.setEmail(logged_user.getEmail());
        PrimeFaces.current().ajax().update(":user_profile_form:user_profile_form_edit");
    }

    public void saveUserProfile() {
        this.userService.updateUser(logged_user_values);
        logged_user = userService.getUserById(logged_user_values.getId());
        PrimeFaces.current().executeScript("PF('manageUserProfileDialog').hide()");
        PrimeFaces.current().ajax().update(":user_profile_form");
        PrimeFaces.current().ajax().update(":form_user_topbar:user_name_label");
//        System.out.println("\n\nName: " + logged_user.getFullName());
    }

    public String login() {
        try {
            UserAuthenticatedDto userAuthenticated = null;
            userAuthenticated = authService.login(this.username, this.password);
            UserDetails userDetails = UserPrincipal.create(userAuthenticated);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // set logged user
            List<UserDto> users = userService.getUsers();
            for (UserDto user : users) {
                if (user.getUsername().equals(this.username)) {
                    logged_user = user;
                    break;
                }
            }
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle("securityMessages", FacesMessage.SEVERITY_INFO, "message_invalid_credentials");
            return null;
        }

        return "login";
    }

    public String logout() {
        return dispatchToUrl("/logout");
    }

    public String profile() {
        return dispatchToUrl("/user_profile");
    }

    public String getUserLogued() {
        return CurrentUserUtils.getFullName();
    }

    private String dispatchToUrl(String url) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        try {
            dispatcher.forward(request, response);
            facesContext.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDto getLogged_user() {
        return logged_user;
    }

    public void setLogged_user(UserDto logged_user) {
        this.logged_user = logged_user;
    }

    public UserDto getLogged_user_values() {
        return logged_user_values;
    }

    public void setLogged_user_values(UserDto logged_user_values) {
        this.logged_user_values = logged_user_values;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
}
