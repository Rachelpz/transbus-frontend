package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;


import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.service.BrandService;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageBrandBean {
	private BrandDto brandDto;
	private BrandDto selectedBrand;
	private List<BrandDto> brands;
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private BrandService brandService;

	public ManageBrandBean() {

	}

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.

	@PostConstruct
	public void init() {
		brands = brands == null ? brandService.getBrands() : brands;

	}
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
			this.selectedBrand.setBrand_id(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)));
			this.selectedBrand.setNewRecord(true);


			this.brands.add(this.selectedBrand);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_brand_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
		}
		else {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_brand_edited");
		}

		PrimeFaces.current().executeScript("PF('manageBrandDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
		PrimeFaces.current().ajax().update("form:dt-brands");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
	}

	//Permite eliminar una marca
	public void deleteBrand() {
		try {
			this.brands.remove(this.selectedBrand);
			this.selectedBrand = null;
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_brand_removed");
			PrimeFaces.current().ajax().update("form:dt-brands");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
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
		return brands;
	}

	public void setBrands(List<BrandDto> brands) {
		this.brands = brands;
	}


}