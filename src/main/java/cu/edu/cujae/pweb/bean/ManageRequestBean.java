package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.RequestDto;
import cu.edu.cujae.pweb.service.RequestService;
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
public class ManageRequestBean {
    private RequestDto requestDto;
    private RequestDto selectedRequest;
    private List<RequestDto> requests;

    private Integer report_requestId;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private RequestService requestService;

    public ManageRequestBean() {

    }

    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {
        this.selectedRequest = new RequestDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    public void saveRequest() {
        if (this.selectedRequest.getRequest_id() == null) {
            this.requestService.createRequest(this.selectedRequest);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_request_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            this.requestService.updateRequest(this.selectedRequest);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_request_edited");
        }

        requests = requestService.getRequests();
        PrimeFaces.current().executeScript("PF('manageRequestDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-requests");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    public void deleteRequest() {
        try {
            boolean canDelete=true;
            canDelete=
            this.requestService.deleteRequest(this.selectedRequest.getRequest_id());

            if (canDelete==false){
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
            }
            else {
                this.selectedRequest = null;

                this.requests = requestService.getRequests();

                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_request_removed");
                PrimeFaces.current().ajax().update("form:dt-requests"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
            }
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    public RequestDto getRequestDto() {
        return requestDto;
    }

    public void setRequestDto(RequestDto requestDto) {
        this.requestDto = requestDto;
    }

    public RequestDto getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(RequestDto selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public List<RequestDto> getRequests() {
        this.requests = requestService.getRequests();
        return this.requests;
    }

    public void setRequests(List<RequestDto> requests) {
        this.requests = requests;
    }

    public RequestService getRequestService() {
        return requestService;
    }

    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    public Integer getReport_requestId() {
        return report_requestId;
    }

    public void setReport_requestId(Integer report_requestId) {
        this.report_requestId = report_requestId;
    }
}