/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.view;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.controller.ControllerEJB;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * RegisterBean that handles requests for creation of users
 * @author marcelmattsson
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable {
    
    @EJB
            ControllerEJB contr;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private String username;
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
    
    public String register(){
        PersonDTO person = new PersonDTO(name,surname,ssn,email,username,password, "applicant");
        boolean valid = contr.validateRegistration(username);
        if (valid) {
            contr.registerUser(person);
            return "/IV1201/index";
        }
        else
            return "/IV1201/registererror";
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
