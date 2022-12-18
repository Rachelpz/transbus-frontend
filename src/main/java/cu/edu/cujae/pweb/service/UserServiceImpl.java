package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestService restService;

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userList = new ArrayList<UserDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/users", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            userList = apiRestMapper.mapList(response, UserDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Integer getUsersSize() {

        List<UserDto> users = new ArrayList<UserDto>();
        Integer size = 0;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/users/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            users = apiRestMapper.mapList(response, UserDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size = users.size();

    }

    @Override
    public UserDto getUserById(String userId) {
        UserDto user = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/users/{userId}");
            String uri = template.expand(userId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            user = apiRestMapper.mapOne(response, UserDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return user;
    }

    @Override
    public String createUser(UserDto user) {
        String message = "";
        ResponseEntity response = restService.POST("/api/v1/users", user, String.class, CurrentUserUtils.getTokenBearer());

        if (response.getStatusCode().isError())
            message = (String) response.getBody();

        return message;
    }

    @Override
    public String registerUser(UserDto user) {
        String message = "";

        ResponseEntity response = restService.POST("/api/v1/users/register", user, String.class);
        if (response.getStatusCode().isError())
            message = (String) response.getBody();

        return message;
    }

    @Override
    public String updateUser(UserDto user) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String message = "";
        ResponseEntity response = restService.PUT("/api/v1/users", params, user, String.class, CurrentUserUtils.getTokenBearer());

        if (response.getStatusCode().isError())
            message = (String) response.getBody();

        return message;
    }

    @Override
    public void deleteUser(String userId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/users/{userId}");
        String uri = template.expand(userId).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserDto user = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/users/username/{username}");
            String uri = template.expand(username).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            user = apiRestMapper.mapOne(response, UserDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return user;
    }

}
