package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.FuelDto;
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
public class ManageFuel_TypeBean {

    private FuelDto fuelDto;
    private FuelDto selectedFuel;
    private List<FuelDto> fuels;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private Fuel_TypeService fuelService;

    public ManageFuel_TypeBean() {

    }

    //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.

    @PostConstruct
    public void init() {
        fuels = fuelService.getFuels();

    }

    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {

        this.selectedFuel = new FuelDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    //Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar la marca
    public void saveFuel() {
        if (this.selectedFuel.getFuel_id() == null) {
            fuelService.createFuel(this.selectedFuel);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_fuel_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        } else {
            fuelService.updateFuel(this.selectedFuel);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_fuel_edited");
        }
        fuels=fuelService.getFuels();
        PrimeFaces.current().executeScript("PF('manageFuelDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-fuels");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    //Permite eliminar una marca
    public void deleteFuel() {
        try {
            fuelService.deleteFuel(this.selectedFuel.getFuel_id());
            this.selectedFuel = null;

            fuels=fuelService.getFuels();

            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_fuel_removed");
            PrimeFaces.current().ajax().update("form:dt-fuels");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }


    public FuelDto getFuelDto() {
        return fuelDto;
    }

    public void setFuelDto(FuelDto fuelDto) {
        this.fuelDto = fuelDto;
    }

    public FuelDto getSelectedFuel() {
        return selectedFuel;
    }

    public void setSelectedFuel(FuelDto selectedFuel) {
        this.selectedFuel = selectedFuel;
    }

    public List<FuelDto> getFuels() {
        return fuels;
    }

    public void setFuels(List<FuelDto> fuels) {
        this.fuels = fuels;
    }




}