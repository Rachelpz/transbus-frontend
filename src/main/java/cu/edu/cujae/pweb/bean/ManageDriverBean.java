package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;


import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.service.DriverService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.service.BrandService;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageDriverBean {
    private DriverDto driverDto;
    private DriverDto selectedDriver;
    private List<DriverDto> drivers;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private DriverService driverService;

    public ManageDriverBean() {

    }

    //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.

    @PostConstruct
    public void init() {
        drivers = drivers == null ? driverService.getDrivers() : drivers;

    }
    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {

        this.selectedDriver = new DriverDto();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }


}