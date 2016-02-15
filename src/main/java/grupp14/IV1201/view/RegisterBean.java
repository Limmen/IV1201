/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.view;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.util.GenericLogger;
import grupp14.IV1201.util.ValidEmail;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;

/**
 * RegisterBean that handles requests for creation of users
 * @author marcelmattsson
 */
@GenericLogger
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable {
    
    @EJB
    ControllerEJB contr;
    @Size(min=1, message="You need to enter a name")
    private String name;
    @Size(min=1, message="You need to enter a surname")
    private String surname;
    private String ssn;
    @ValidEmail
    private String email;
    @Size(min=2, max=16, message="username needs to be between"
            + " 2 and 16 characters long")
    private String username;
    @Size(min=6, max=16, message="Password needs to be between"
            + " 6 and 16 characters long")
    private String password;
    private String roll_id;
    public RegisterBean(){
        
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getSsn() {
        return ssn;
    }
    
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String mail) {
        this.email = mail;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRoll_id() {
        return roll_id;
    }    
    public void setRoll_id(String roll_id) {
        this.roll_id = roll_id;
    }
    
    public void register() throws IOException, NoSuchAlgorithmException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        PersonDTO person = new PersonDTO(name,surname,ssn,email,username,password, "applicant");
        boolean valid = contr.validateRegistration(username);
        if (valid) {
            contr.registerUser(person);
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        }
        else
            externalContext.redirect(externalContext.getRequestContextPath() + "/registererror.xhtml");
    }
    
    private void clear(){
        name = "";
        surname = "";
        ssn = "";
        email = "";
        password="";
        username ="";
    }
}
