package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.CountryDto;
import cu.edu.cujae.pweb.service.CountryService;
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
public class ManageCountryBean {
    private CountryDto countryDto;
    private CountryDto selectedCountry;
    private List<CountryDto> countries;

    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private CountryService countryService;

    public ManageCountryBean() {

    }

    public CountryDto getCountryDto() {
        return countryDto;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }

    public CountryDto getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(CountryDto selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<CountryDto> getCountries() {
        this.countries = countryService.getCountries();
        return this.countries;
    }

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
