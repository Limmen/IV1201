/*
 * Classname: RegisterBean
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
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
 * Managed bean representing the interface between the model and the register-page.
 */
@GenericLogger
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable
{
    @EJB
    ControllerEJB contr;
    @Size(min=1, message="You need to enter a name")
    private String name;
    @Size(min=1, message="You need to enter a surname")
    private String surname;
    private String ssn;
    @ValidEmail
    private String email;
    @Size(min=3, max=16, message="username needs to be between"
            + " 3 and 16 characters long")
    private String username;
    @Size(min=6, max=30, message="Password needs to be between"
            + " 6 and 16 characters long")
    private String password;
    private String roll_id;

    /**
     * Class constructor
     */
    public RegisterBean()
    {
        
    }
    
    /**
     * Called when user clicks "register" button.
     * 
     * This method will try to register the user if the input-data is sufficient.
     * 
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void register() throws IOException, NoSuchAlgorithmException
    {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        PersonDTO person = new PersonDTO(name,surname,ssn,email,username,
                password, "applicant");
        boolean valid = contr.validateRegistration(username);
        if (valid) {
            contr.registerUser(person);
            externalContext.redirect(externalContext.getRequestContextPath()
                    + "/index.xhtml");
        }
        else
            externalContext.redirect(externalContext.getRequestContextPath()
                   + "/registererror.xhtml");
    }
    
    private void clear(){
        name = "";
        surname = "";
        ssn = "";
        email = "";
        password="";
        username ="";
    }
     /**
     *
     * @return name of the given RegisterBean
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     *
     * @param name a name that has been set by the user from register.xhtml
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     *
     * @return surname of the given RegisterBean
     */
    public String getSurname()
    {
        return surname;
    }
    
    /**
     *
     * @param surname a surname that has been set by the user  from register.xhtml
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    
    /**
     *
     * @return ssn of the given RegisterBean
     */
    public String getSsn()
    {
        return ssn;
    }
    
    /**
     *
     * @param ssn a ssn that has been set by the user from register.xhtml
     */
    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }
    
    /**
     *
     * @return email of the given RegisterBean
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     *
     * @param mail a email that has been set by the user from register.xhtml
     */
    public void setEmail(String mail)
    {
        this.email = mail;
    }
    
    /**
     *
     * @return username of the given RegisterBean
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     *
     * @param username a username that has been set by the user from register.xhtml
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     *
     * @return password of the given RegisterBean
     */
    public String getPassword() 
    {
        return password;
    }
    
    /**
     *
     * @param password a password that has been set by the user from register.xhtml
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     *
     * @return roll of the given RegisterBean
     */
    public String getRoll_id()
    {
        return roll_id;
    }    

    /**
     *
     * @param roll_id a role that has been set by the user from register.xhtml
     */
    public void setRoll_id(String roll_id)
    {
        this.roll_id = roll_id;
    }
    
}
