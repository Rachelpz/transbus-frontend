package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.service.DriverService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.UUID;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageDriverBean {
    private DriverDto driverDto;
    private DriverDto selectedDriver;
    private List<DriverDto> drivers;

    private Integer sizeDrivers = 0;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private DriverService driverService;

    public ManageDriverBean() {

    }

    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {
        this.selectedDriver = new DriverDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    public void saveDriver() {
        if (this.selectedDriver.getDriver_id() == null) {
            this.driverService.createDriver(this.selectedDriver);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_driver_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            this.driverService.updateDriver(this.selectedDriver);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_driver_edited");
        }

        this.drivers = driverService.getDrivers();
        this.sizeDrivers = drivers.size();
        PrimeFaces.current().executeScript("PF('manageDriverDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-drivers");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    public void deleteDriver() {
        try {
            boolean canDelete=true;
            canDelete=this.driverService.deleteDriver(this.selectedDriver.getDriver_id());
            if (canDelete==false){
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
            }
            else
            {
                this.selectedDriver = null;

                this.drivers = driverService.getDrivers();

                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_driver_removed");
                PrimeFaces.current().ajax().update("form:dt-drivers"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
            }


        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    public DriverDto getDriverDto() {
        return driverDto;
    }

    public void setDriverDto(DriverDto driverDto) {
        this.driverDto = driverDto;
    }

    public DriverDto getSelectedDriver() {
        return selectedDriver;
    }

    public void setSelectedDriver(DriverDto selectedDriver) {
        this.selectedDriver = selectedDriver;
    }

    public List<DriverDto> getDrivers() {
        this.drivers = driverService.getDrivers();
        return this.drivers;
    }

    public void setDrivers(List<DriverDto> drivers) {
        this.drivers = drivers;
    }

    public DriverService getDriverService() {
        return driverService;
    }

    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    public Integer getSizeDrivers() {
        this.sizeDrivers = driverService.getDriversSize();
        return this.sizeDrivers;
    }

    public void setSizeDrivers(Integer sizeDrivers) {
        this.sizeDrivers = sizeDrivers;
    }
}