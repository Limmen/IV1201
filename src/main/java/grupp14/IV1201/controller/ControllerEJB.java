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
import grupp14.IV1201.integration.DAO.DataAccessObject;
import grupp14.IV1201.integration.entities.Application;
import grupp14.IV1201.integration.entities.Expertise;
import grupp14.IV1201.integration.entities.Person;
import grupp14.IV1201.model.HttpSessionBean;
import grupp14.IV1201.model.LoginEJB;
import grupp14.IV1201.util.PDFManager;
import grupp14.IV1201.model.RegisterEJB;
import grupp14.IV1201.util.LogManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

/**
 * Application controller. Encapsulates system functionality into an API.
 * @author kim
 */
@Stateless
public class ControllerEJB 
{    
    @EJB
    DataAccessObject dao;
    @EJB
    private LoginEJB login;
    @EJB
    private RegisterEJB register;
    @EJB
    private HttpSessionBean session;
    
    private LogManager logManager;
    
    /**
     * This method is called by the cdi-container after dependency-injection
     * but before the class is put into service.
     */
    @PostConstruct
    public void init()
    {
        logManager = new LogManager();
        login.setLogManager(logManager);
        register.setLogManager(logManager);
        dao.setLogManager(logManager);
    }
    
    /**
     * Registers a user.
     * 
     * @param person data-transfer-object containing specification of the person to register.
     * @throws NoSuchAlgorithmException thrown when the encryption phase in the model was invalid.
     */
    public void registerUser(PersonDTO person) throws NoSuchAlgorithmException
    {
        dao.registerPerson(person);
    }

    /**
     * Validates a login.
     * 
     * @param username username to validate
     * @param password password to validate
     * @return true if login was successful otherwise false.
     * @throws NoSuchAlgorithmException thrown when the encryption phase in the model was invalid.
     */
    public boolean validateLogin(String username, String password) throws NoSuchAlgorithmException 
    {
       return login.validateLogin(username, password);
    }

    /**
     * Fetches the role by the username of a registered user.
     * 
     * @param username username of the user
     * @return Role of the user. Null if the role cannot be found.
     */
    public String getRole(String username)
    {
        return dao.getPersonRole(username);
    }

    /**
     * Validates a registration by checking if the username is available.
     * 
     * @param username username to check
     * @return true if the username is free, otherwise false.
     */
    public boolean validateRegistration(String username)
    {
        return register.validateRegistration(username);
    }

    /**
     * Returns the HTTP-session
     * @return http-session
     */
    public HttpSession getSession()
    {
        return session.getSession();
    }

    /**
     * Returns the username of the current session.
     * 
     * @return Username
     */
    public String getUsername()
    {
        return session.getUserName();
    }

    /**
     * Fetches the list of expertises
     * @return list of expertises
     */
    public List<Expertise> getExpertiseList()
    {
        return dao.getExpertiseList();
    }

    /**
     * Fetches the list of applications
     * @return list of applications
     */
    public List<Application> getApplicationList()
    {
        return dao.getApplicationList();
    }

    /**
     * Fethes a list of applications for a certain user.
     * 
     * @param username username of the user
     * @return list of applications
     */
    public List<Application> getApplicationList(String username)
    {
        return dao.getApplicationList(username);
    }
    /**
     * Creates an application.
     * 
     * @param application data-transfer-object of application details.
     * @throws java.security.NoSuchAlgorithmException thrown when the encryption phase in the model 
     * was invalid.
     */
    public void apply(ApplicationDTO application) throws NoSuchAlgorithmException
    {
        dao.placeApplication(application);
    }
    
    /**
     * Creates a PDF-file of some application.
     * 
     * @param application application to create the pdf-file from.
     * @throws IOException IOException thrown when the specified URL cannot be found for the 
     * redirection.
     * @throws DocumentException Thrown when a error occurs in the creation of the pdf document.
     */
    public void createPDF(Application application) throws IOException, DocumentException
    {
        PDFManager.createPDF(application);
    }
       
    /**
     * Fetches a person by username.
     * 
     * @param username username of the person
     * @return person
     */
    public Person getPerson(String username)
    {
        return dao.getPersonByUsername(username);
    }

    /**
     * Fetches a expertise by the name.
     * 
     * @param expertise expertise name
     * @return expertise
     */
    public Expertise getExpertise(String expertise)
    {
        return dao.getExpertiseByName(expertise);
    }  
}
