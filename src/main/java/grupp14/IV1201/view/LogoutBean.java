/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.view;

import grupp14.IV1201.controller.ControllerEJB;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * ManagedBean to logout  user.
 * @author kim
 */
@Named(value = "logoutBean")
@RequestScoped
public class LogoutBean implements Serializable {
    
    @EJB
    private ControllerEJB contr;
    private String logout;
    
    /**
     *
     * @return
     */
    public String getLogout() {
        return logout;
    }
    
    /**
     * This method is called when a logged in user clicks logout-button.
     * @return destination
     */
    public String logout() {
        return invalidateSession((HttpServletRequest) 
                FacesContext.getCurrentInstance().getExternalContext().getRequest());
    }
    String invalidateSession(HttpServletRequest request){
        try{
            request.getSession().invalidate();
            request.logout();
        }
        catch (ServletException e) {
            return "/loginerror?faces-redirect=true";//redirect to error-page
        }
        return "/index?faces-redirect=true"; //redirect to index
    }
    
}
