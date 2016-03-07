/* 
 * Classname: ControllerEJB
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.controller;

import com.lowagie.text.DocumentException;
import grupp14.IV1201.DTO.ApplicationDTO;
import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.entities.Application;
import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;
import grupp14.IV1201.model.ApplicationEJB;
import grupp14.IV1201.model.HttpSessionBean;
import grupp14.IV1201.model.LoginEJB;
import grupp14.IV1201.util.PDFManager;
import grupp14.IV1201.model.RegisterEJB;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kim
 */
@Stateless
public class ControllerEJB 
{    
    
    @EJB
    private LoginEJB login;
    @EJB
    private RegisterEJB register;
    @EJB
    private HttpSessionBean session;
    @EJB
    private ApplicationEJB app;
    
    /**
     *
     * @param person
     * @throws NoSuchAlgorithmException
     */
    public void registerUser(PersonDTO person) throws NoSuchAlgorithmException
    {
        register.register(person);
    }

    /**
     *
     * @param username
     */
    public void unRegisterUser(String username)
    {
        
    }

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public boolean validateLogin(String username, String password) throws NoSuchAlgorithmException 
    {
       return login.validateLogin(username, password);
    }

    /**
     *
     * @param username
     * @return
     */
    public String getRole(String username)
    {
        return login.getRole(username);
    }

    /**
     *
     * @param username
     * @return
     */
    public boolean validateRegistration(String username)
    {
        return register.validateRegistration(username);
    }

    /**
     *
     * @return
     */
    public HttpSession getSession()
    {
        return session.getSession();
    }

    /**
     *
     * @return
     */
    public String getUsername()
    {
        return session.getUserName();
    }

    /**
     *
     * @return
     */
    public List<Expertise> getExpertiseList()
    {
        return app.getExpertiseList();
    }

    /**
     *
     * @return
     */
    public List<Application> getApplicationList()
    {
        return app.getApplicationList();
    }

    /**
     *
     * @param username
     * @return
     */
    public List<Application> getApplicationList(String username)
    {
        return app.getApplicationList(username);
    }
    /**
     *
     * @param application
     * @throws java.security.NoSuchAlgorithmException
     */
    public void apply(ApplicationDTO application) throws NoSuchAlgorithmException
    {
        app.placeApplication(application);
    }
    
    /**
     *
     * @param application
     * @throws IOException
     * @throws DocumentException
     */
    public void createPDF(Application application) throws IOException, DocumentException{
        PDFManager.createPDF(application);
    }
       
    /**
     *
     * @param username
     * @return
     */
    public Person getPerson(String username){
        return app.getPerson(username);
    }

    /**
     *
     * @param expertise
     * @return
     */
    public Expertise getExpertise(String expertise){
        return app.getExpertise(expertise);
    }  
}
