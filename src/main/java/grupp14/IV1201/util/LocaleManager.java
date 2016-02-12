/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.util;

import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Sets the correct locale for the entire application.
 */
@Named(value="localeManager")
@ApplicationScoped
public class LocaleManager {
    
    private String locale;
    
    public void changeLocale() {
        locale = getLanguageCode();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(getLanguageCode()));
    }
    
    public String getLocale() {
        return locale;
    }
    
    private String getLanguageCode() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
    }
}
