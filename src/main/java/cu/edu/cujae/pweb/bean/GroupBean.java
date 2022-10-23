package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
//import javax.faces.bean.SessionScoped;

@ManagedBean
//@SessionScoped
public class GroupBean {
    private String group_name;
    private Integer paxamount;
    private String country_name;

    public GroupBean() {
        // TODO Auto-generated constructor stub
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getPaxamount() {
        return paxamount;
    }

    public void setPaxamount(Integer paxamount) {
        this.paxamount = paxamount;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
