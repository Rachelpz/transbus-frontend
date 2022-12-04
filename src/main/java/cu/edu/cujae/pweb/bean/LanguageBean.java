package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.Map;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="languageBean")
@SessionScoped
public class LanguageBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private String localeCode="es";
    private Locale locale;

    private String code;
    private static Map<String,String> countries;
    static{
        countries = new LinkedHashMap<String,String>();
        countries.put(" Spanish", "es");
        countries.put("English", "en"); //label, value

    }
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    @PostConstruct
    public void init() {
        localeCode = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().getLanguage();
        code= FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().getLanguage();
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();

    }

    public Map<String, String> getCountriesInMap() {
        return countries;
    }


    public String getLocaleCode() {
        return localeCode;
    }


    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e){

        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String,String> entry : countries.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)){
                locale = new Locale(entry.getValue());
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(locale);
               code = locale.getLanguage();

            }
        }
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}