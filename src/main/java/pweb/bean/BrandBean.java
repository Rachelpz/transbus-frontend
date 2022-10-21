package pweb.bean;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
//import javax.faces.bean.SessionScoped;

@ManagedBean
//@SessionScoped
public class BrandBean {
    private String brand_name;
    private Integer seats_numb;
    private String fuel_type;
    private Integer fuel_consumtion;
    private String fuel;

    public BrandBean() {
        // TODO Auto-generated constructor stub
    }
    public BrandBean(String brand_name, Integer seats_numb, String fuel_type, Integer fuel_consumtion, String fuel) {
        this.brand_name = brand_name;
        this.seats_numb = seats_numb;
        this.fuel_type = fuel_type;
        this.fuel_consumtion = fuel_consumtion;
        this.fuel = fuel;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getSeats_numb() {
        return seats_numb;
    }

    public void setSeats_numb(Integer seats_numb) {
        this.seats_numb = seats_numb;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public Integer getFuel_consumtion() {
        return fuel_consumtion;
    }

    public void setFuel_consumtion(Integer fuel_consumtion) {
        this.fuel_consumtion = fuel_consumtion;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }


    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
