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
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    private boolean registerSuccess = false;
    private boolean registerError = false;

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
     * @throws NoSuchAlgorithmException thrown when the encryption phase in the model was invalid.
     */
    public void register() throws NoSuchAlgorithmException
    {
        PersonDTO person = new PersonDTO(name,surname,ssn,email,username,
                password, "applicant");
        boolean valid = contr.validateRegistration(username);
        if (valid) {
            contr.registerUser(person);
            registerSuccess = true;
        }
        else
            registerError = true;
    }
    
    private void clear(){
        name = "";
        surname = "";
        ssn = "";
        email = "";
        password="";
        username ="";
    }
<<<<<<< HEAD
     /**
     *
     * @return name of the given RegisterBean
=======
    
    /**
     * getName
     * @return name of the person to register
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public String getName() 
    {
        return name;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param name a name that has been set by the user from register.xhtml
=======
     * Updates name
     * @param name name of the person to register
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
<<<<<<< HEAD
     *
     * @return surname of the given RegisterBean
=======
     * getSurname
     * @return surname of the person to register
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public String getSurname()
    {
        return surname;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param surname a surname that has been set by the user  from register.xhtml
=======
     * Updates the surname of the person to register
     * @param surname
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    
    /**
<<<<<<< HEAD
     *
     * @return ssn of the given RegisterBean
=======
     * getSsn
     * @return ssn of the person to register
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public String getSsn()
    {
        return ssn;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param ssn a ssn that has been set by the user from register.xhtml
=======
     * Updates the ssn of the person to register
     * @param ssn of the person to register
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }
    
    /**
<<<<<<< HEAD
     *
     * @return email of the given RegisterBean
=======
     * getEmail
     * @return email of the person to register
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * Updates the email of the person to register
     * @param mail of the person to register
     */
    public void setEmail(String mail)
    {
        this.email = mail;
    }
    
    /**
     * getUsername
     * @return username of the person to register
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Updates the username of the person to register
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * getPassword
     * @return password of the person to register
     */
    public String getPassword() 
    {
        return password;
    }
    
    /**
     * Updates the password of the person to register
     * @param password of the person to register
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * getRoll_id
     * @return roll_id of the person to register
     */
    public String getRoll_id()
    {
        return roll_id;
    }    

    /**
     * Updates the roll_id of the person to register 
     * @param roll_id of the person to register
     */
    public void setRoll_id(String roll_id)
    {
        this.roll_id = roll_id;
    }

    /**
     * isRegisterSuccess
     * @return boolean wether the most recent registration was successful or not
     */
    public boolean isRegisterSuccess() {
        return registerSuccess;
    }

    /**
     * isRegisterError
     * @return boolean wether the most recent registration was failed or not
     */
    public boolean isRegisterError() {
        return registerError;
    }
    
    
}
