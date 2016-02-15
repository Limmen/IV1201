/*
 * Classname: LocaleManager
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
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
    
    /**
     *
     */
    public void changeLocale() 
    {
        locale = getLanguageCode();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(getLanguageCode()));
    }
    
    /**
     *
     * @return
     */
    public String getLocale()
    {
        return locale;
    }
    
    private String getLanguageCode() 
    {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("languageCode");
    }
}
