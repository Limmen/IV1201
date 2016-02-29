/* 
 * Classname: HttpSessionBean
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD

=======
/**
 *
 * @author kim
 */
@Stateless
>>>>>>> origin/master
public class HttpSessionBean
{

    FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    /**
     *
     * @return
     */
    public HttpSession getSession() 
    {
        return (HttpSession) getFacesContext()
                .getExternalContext().getSession(false);
    }
    
    /**
     *
     * @return
     */
    public HttpServletRequest getRequest()
    {
        return (HttpServletRequest) getFacesContext()
                .getExternalContext().getRequest();
    }
    
    /**
     *
     * @return
     */
    public String getUserName()
    {
        HttpSession session = (HttpSession) getFacesContext()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
    
}
