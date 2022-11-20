package cu.edu.cujae.pweb.bean;

import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

@Named(value = "changeTheme")
@SessionScope
public class ChangeTheme implements Serializable {

    private static final long serialVersionUID = 1L;
    private String theme ="arya";
    private Map<String, String> themes;

    public Map<String, String> getThemes() {
        themes = new TreeMap<String, String>();
        themes.put("Light", "saga");
        themes.put("Dark Light", "vela");
        themes.put("Dark", "arya");
        return themes;
    }

    public void setThemes(Map<String, String> themes) {
        this.themes = themes;
    }

    public ChangeTheme() { }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String change(){ return null; }
}