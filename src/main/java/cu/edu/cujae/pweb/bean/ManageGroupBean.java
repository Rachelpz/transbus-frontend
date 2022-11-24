package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.GroupDto;
import cu.edu.cujae.pweb.service.GroupService;
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
public class ManageGroupBean {
    private GroupDto groupDto;
    private GroupDto selectedGroup;
    private List<GroupDto> groups;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private GroupService groupService;

    public ManageGroupBean() {

    }

    //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.

    @PostConstruct
    public void init() {
        groups = groups == null ? groupService.getGroups() : groups;

    }
    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {

        this.selectedGroup = new GroupDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    //Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar el grupo
    public void saveGroup() {
        if (this.selectedGroup.getGroup_id() == null) {
            this.selectedGroup.setGroup_id(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)));
            this.selectedGroup.setNewRecord(true);


            this.groups.add(this.selectedGroup);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_group_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_group_edited");
        }

        PrimeFaces.current().executeScript("PF('manageGroupDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-groups");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    //Permite eliminar un grupo
    public void deleteGroup() {
        try {
            this.groups.remove(this.selectedGroup);
            this.selectedGroup = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_group_removed");
            PrimeFaces.current().ajax().update("form:dt-groups");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    public GroupDto getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(GroupDto selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }


}