package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
//import javax.faces.bean.SessionScoped;

@ManagedBean
//@SessionScoped
public class VehicleBean {
    private String plate_numb;
    private Float planned_fuel;
    private String first_driver;
    private String second_driver;
    private String brand;

    public VehicleBean() {
        // TODO Auto-generated constructor stub
    }


    public String getPlate_numb() {
        return plate_numb;
    }

    public void setPlate_numb(String plate_numb) {
        this.plate_numb = plate_numb;
    }

    public Float getPlanned_fuel() {
        return planned_fuel;
    }

    public void setPlanned_fuel(Float planned_fuel) {
        this.planned_fuel = planned_fuel;
    }

    public String getFirst_driver() {
        return first_driver;
    }

    public void setFirst_driver(String first_driver) {
        this.first_driver = first_driver;
    }

    public String getSecond_driver() {
        return second_driver;
    }

    public void setSecond_driver(String second_driver) {
        this.second_driver = second_driver;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
