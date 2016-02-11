/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.view;

import grupp14.IV1201.controller.ControllerEJB;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kim
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
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
    
    public void login() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        boolean valid = contr.validateLogin(username, password);
        if (valid) {
            HttpSession session = contr.getSession();
            session.setAttribute("username", username);
            externalContext.redirect(externalContext.getRequestContextPath() + "/applicant/index.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            externalContext.redirect(externalContext.getRequestContextPath() + "/errorlogin.xhtml");
        }
    }
    //logout event, invalidate session
    public void logout() {
        HttpSession session = contr.getSession();
        session.invalidate();
    }
    public boolean isLoggedIn(){
        return contr.getSession().getAttribute(username) != null;
    }
}
