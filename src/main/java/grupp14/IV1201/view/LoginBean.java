/*
* Classname: LoginBean
* Version: 0.1
* Date: 15-2-2016
* Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
*/

package grupp14.IV1201.view;

import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.util.GenericLogger;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    private boolean applicant = false;
    private boolean recruit = false;
    private boolean failed = false;
    private boolean logout = false;
    
    /**
     * This method is called when the user clicks the "login" button.
     *
     * The method will validate the user's credentials and redirect to the
     * suitable page.
     *
     * @throws NoSuchAlgorithmException thrown when the encryption phase in the model was invalid.
     */
    
    public void login() throws NoSuchAlgorithmException
    {
        boolean valid = contr.validateLogin(username, password);
        String role = contr.getRole(username);
        if (valid) {
            HttpSession session = contr.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            navigation(role);
        } else {
            failedLogin();
        }
    }
    
    /**
     * This method will invalidate the user's session.
     */
    public void logout()
    {
        HttpSession session = contr.getSession();
        session.invalidate();
        logout = true;
        failed = false;
        recruit = false;
        applicant = false;
    }
    
    private void failedLogin()
    {
        java.util.logging.Logger.getLogger("grupp14.IV1201").log(Level.INFO, "FAILED LOGIN ATTEMPT");
        failed = true;
        recruit = false;
        applicant = false;
        logout = false;
    }
    
    private void navigation(String role)
    {
        if(role.equals("applicant")){
            applicant = true;
            recruit = false;
            failed = false;
            logout = false;
        }
        if(role.equals("recruit")){
            recruit = true;
            applicant = false;
            failed = false;
            logout = false;
        }
    }
    
    /**
     * getUsername
     * @return username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Updates username
     * @param loginUsername new username
     */
    public void setUsername(String loginUsername)
    {
        this.username = loginUsername;
    }
    
    /**
     * getPassword
     * @return password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Updates password
     * @param loginPassword new password
     */
    public void setPassword(String loginPassword)
    {
        this.password = loginPassword;
    }

    /**
     * isApplicant 
     * @return boolean wether the login was a applicant
     */
    public boolean isApplicant() 
    {
        return applicant;
    }

    /**
     * isRecruit
     * @return boolean wether the login was a recruit
     */
    public boolean isRecruit() 
    {
        return recruit;
    }

    /**
     * isFailed
     * @return boolean wether the login was failed
     */
    public boolean isFailed() 
    {
        return failed;
    }

    /**
     * isLogout
     * @return boolean if the user clicked logout
     */
    public boolean isLogout() 
    {
        return logout;
    }
    
    
}
