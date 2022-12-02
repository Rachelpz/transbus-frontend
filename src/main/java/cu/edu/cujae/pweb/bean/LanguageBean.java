package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class LanguageBean {

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = JsfUtils.getCurrentLocale();

    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }


    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}
