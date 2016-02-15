/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.view;

import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.util.GenericLogger;
import grupp14.IV1201.util.LoginLogger;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kim
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    @EJB
    private ControllerEJB contr;
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String loginUsername) {
        this.username = loginUsername;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String loginPassword) {
        this.password = loginPassword;
    }
    @GenericLogger
    public void login() throws NoSuchAlgorithmException, IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        boolean valid = contr.validateLogin(username, password);
        String role = contr.getRole(username);
        if (valid) {
            HttpSession session = contr.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            if(role.equals("applicant"))
            externalContext.redirect(externalContext.getRequestContextPath() + "/applicant/index.xhtml");
            else if(role.equals("recruit"))
                externalContext.redirect(externalContext.getRequestContextPath() + "/recruit/index.xhtml");
        } else {
            failedLogin(externalContext);
        }
    } 
    
    //logout event, invalidate session
    @GenericLogger
    public void logout() {
        HttpSession session = contr.getSession();
        session.invalidate();
    }
    @LoginLogger
    private void failedLogin(ExternalContext externalContext)throws IOException{
        externalContext.redirect
        (externalContext.getRequestContextPath() + "/loginerror.xhtml");        
    }    
}
