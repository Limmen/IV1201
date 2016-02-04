/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 * Managed bean/CDI-bean that represents the state of the user.
 * @author kim
 */
@Named(value = "userinfoBean")
@SessionScoped
public class UserinfoBean implements Serializable{
    
    private String username;
    private String role;
    
    /**
     * Method to initialize role and username. This is called when the user
     * first access a authorized page.
     */
    @PostConstruct
    public void init(){
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("recruit"))
            role = "recruit";
        else{
            if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("applicant"))
                role = "applicant";
            else
                role = "missing";
        }
        System.out.println("username is: " + username);
    }

    /**
     * getUsername
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getRole
     * @return role
     */
    public String getRole() {
        return role;
    }
    
}
