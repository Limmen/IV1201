/*
* Classname: LoginBean
* Version: 0.1
* Date: 15-2-2016
* Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
*/

package grupp14.IV1201.view;

import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.util.GenericLogger;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

/**
 * Managed bean representing the interface between the model and the
 * login-page.
 * @author kim
 */
@GenericLogger
@Named(value = "loginBean")
@RequestScoped
public class LoginBean
{
    @EJB
    private ControllerEJB contr;
    @Size(min=3, max=16, message="username needs to be between"
            + " 3 and 16 characters long")
    private String username;
    @Size(min=6, max=30, message="Password needs to be between"
            + " 6 and 16 characters long")
    private String password;
    
    /**
     * This method is called when the user clicks the "login" button.
     *
     * The method will validate the user's credentials and redirect to the
     * suitable page.
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    
    public void login() throws NoSuchAlgorithmException, IOException
    {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        boolean valid = contr.validateLogin(username, password);
        String role = contr.getRole(username);
        if (valid) {
            HttpSession session = contr.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            if(role.equals("applicant"))
                externalContext.redirect(externalContext.getRequestContextPath()
                        + "/applicant/index.xhtml");
            else if(role.equals("recruit"))
                externalContext.redirect(externalContext.getRequestContextPath()
                        + "/recruit/index.xhtml");
        } else {
            failedLogin(externalContext);
        }
    }
    
    /**
     * This method will invalidate the user's session.
     * @throws java.io.IOException
     */

    public void logout() throws IOException
    {
        HttpSession session = contr.getSession();
        session.invalidate();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect
                (externalContext.getRequestContextPath() + "/index.xhtml");
        
    }
    private void failedLogin(ExternalContext externalContext)throws IOException
    {
        java.util.logging.Logger.getLogger("grupp14.IV1201").log(Level.INFO, "FAILED LOGIN ATTEMPT");
        externalContext.redirect
                (externalContext.getRequestContextPath() + "/loginerror.xhtml");
    }
    
    /**
     *
     * @return username from the given LoginBean
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     *
     * @param loginUsername a username that has been set by the user from login.xhtml
     */
    public void setUsername(String loginUsername)
    {
        this.username = loginUsername;
    }
    
    /**
     *
     * @return password from the given LoginBean
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     *
     * @param loginPassword a password that has been set by the user from login.xhtml
     */
    public void setPassword(String loginPassword)
    {
        this.password = loginPassword;
    }
}
