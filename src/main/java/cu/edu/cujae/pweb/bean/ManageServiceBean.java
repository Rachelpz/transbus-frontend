package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.ServiceDto;
import cu.edu.cujae.pweb.service.ServiceService;
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
public class ManageServiceBean {
    private ServiceDto serviceDto;
    private ServiceDto selectedService;
    private List<ServiceDto> services;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private ServiceService serviceService;

    public ManageServiceBean() {

    }

    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {
        this.selectedService = new ServiceDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    public void saveService() {
        if (this.selectedService.getService_id() == null) {
            this.serviceService.createService(this.selectedService);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_service_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            this.serviceService.updateService(this.selectedService);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_service_edited");
        }

        services = serviceService.getServices();
        PrimeFaces.current().executeScript("PF('manageServiceDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-services");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    public void deleteService() {
        try {
            boolean canDelete=true;
            canDelete=
            this.serviceService.deleteService(this.selectedService.getService_id());

            if (canDelete==false){
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
            }
            else {
                this.selectedService = null;

                this.services = serviceService.getServices();

                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_service_removed");
                PrimeFaces.current().ajax().update("form:dt-services"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
            }

            } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    public ServiceDto getServiceDto() {
        return serviceDto;
    }

    public void setServiceDto(ServiceDto serviceDto) {
        this.serviceDto = serviceDto;
    }

    public ServiceDto getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(ServiceDto selectedService) {
        this.selectedService = selectedService;
    }

    public List<ServiceDto> getServices() {
        this.services = serviceService.getServices();
        return this.services;
    }

    public void setServices(List<ServiceDto> services) {
        this.services = services;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
}