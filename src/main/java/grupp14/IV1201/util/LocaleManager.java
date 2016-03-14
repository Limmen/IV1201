/*
 * Classname: LocaleManager
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.util;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * This class contains state that decides the correct locale for the entire application.
 */
@Named(value="localeManager")
@SessionScoped
public class LocaleManager implements Serializable {
    
    private Locale locale;
    
    /**
     * This method is called by the cdi-container after dependency-injection
     * but before the class is put into service.
     * 
     * Initializes the locale.
     */
    @PostConstruct
    public void init(){
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }
    /**
     * Changes the locale of the application.
     */
    public void changeLocale() 
    {
        locale = new Locale(getLanguageCode());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
    
    /**
     * getLocale
     * @return the current locale
     */
    public Locale getLocale()
    {
        return locale;
    }
    
    private String getLanguageCode() 
    {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("languageCode");
    }
}
