package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.dto.FuelDto;
import cu.edu.cujae.pweb.dto.VehicleDto;
import cu.edu.cujae.pweb.service.BrandService;
import cu.edu.cujae.pweb.service.Fuel_TypeService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageBrandBean {
    private BrandDto brandDto;
    private BrandDto selectedBrand;
    private List<BrandDto> brands;
    private List<VehicleDto> vehiclesById;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    private String brandName;


    private Integer sizeBrands=0;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private BrandService brandService;

    public ManageBrandBean() {

    }

    //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.


    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {
        this.selectedBrand = new BrandDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    //Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar la marca
    public void saveBrand() {
        if (this.selectedBrand.getBrand_id() == null) {
            this.brandService.createBrand(this.selectedBrand);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_brand_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        } else {
            this.brandService.updateBrand(this.selectedBrand);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_brand_edited");
        }

        this.brands = brandService.getBrands();
        this.sizeBrands=brands.size();
        PrimeFaces.current().executeScript("PF('manageBrandDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-brands");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    //Permite eliminar una marca
    public void deleteBrand() {
        try {
            boolean canDelete=true;
            canDelete=this.brandService.deleteBrand(this.selectedBrand.getBrand_id());
            if(canDelete==false){
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
            }else
            {
                this.selectedBrand = null;

                //load datatable again with new values
                this.brands = brandService.getBrands();

                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_brand_removed");
                PrimeFaces.current().ajax().update("form:dt-brands");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

            }
            } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    public BrandDto getBrandDto() {
        return brandDto;
    }

    public void setBrandDto(BrandDto brandDto) {
        this.brandDto = brandDto;
    }

    public BrandDto getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(BrandDto selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    public List<BrandDto> getBrands() {
        this.brands = brandService.getBrands();
        return this.brands;
    }

    public void setBrands(List<BrandDto> brands) {
        this.brands = brands;
    }

    public Integer getSizeBrands() {
        this.sizeBrands = brandService.getBrandsSize();
        return this.sizeBrands;
    }

    public void setSizeBrands(Integer sizeBrands) {
        this.sizeBrands = sizeBrands;
    }
    public List<VehicleDto> getVehiclesById(String brandName) {
        this.vehiclesById = brandService.getVehiclesByIdBrand(brandName);
        return this.vehiclesById;
    }


}