package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.ContractDto;
import cu.edu.cujae.pweb.service.ContractService;
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
public class ManageContractBean {
    private ContractDto contractDto;
    private ContractDto selectedContract;
    private List<ContractDto> contracts;

    private Integer sizeContracts = 0;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private ContractService contractService;

    public ManageContractBean() {

    }

    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {
        this.selectedContract = new ContractDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    public void saveContract() {
        if (this.selectedContract.getContract_id() == null) {
            this.contractService.createContract(this.selectedContract);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_contract_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            this.contractService.updateContract(this.selectedContract);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_contract_edited");
        }

        this.contracts = contractService.getContracts();
        this.sizeContracts = contracts.size();
        PrimeFaces.current().executeScript("PF('manageContractDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-contracts");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    public void deleteContract() {
        try {
            this.contractService.deleteContract(this.selectedContract.getContract_id());
            this.selectedContract = null;

            this.contracts = contractService.getContracts();

            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_contract_removed");
            PrimeFaces.current().ajax().update("form:dt-contracts"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    public ContractDto getContractDto() {
        return contractDto;
    }

    public void setContractDto(ContractDto contractDto) {
        this.contractDto = contractDto;
    }

    public ContractDto getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(ContractDto selectedContract) {
        this.selectedContract = selectedContract;
    }

    public List<ContractDto> getContracts() {
        this.contracts = contractService.getContracts();
        return this.contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

    public ContractService getContractService() {
        return contractService;
    }

    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    public Integer getSizeContracts() {
        this.sizeContracts = contractService.getContractsSize();
        return this.sizeContracts;
    }

    public void setSizeContracts(Integer sizeContracts) {
        this.sizeContracts = sizeContracts;
    }

    public List<ContractDto> getContractsByRequest(Integer requestId) {
        return contractService.getContractByRequest(requestId);
    }
}