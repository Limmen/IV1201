/* 
 * Classname: ApplicationEJB
 * Version: 0.1
 * Date: 14-3-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */
package grupp14.IV1201.model;

import grupp14.IV1201.DTO.ApplicationDTO;
import grupp14.IV1201.entities.Application;
import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;
import grupp14.IV1201.util.LogManager;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * EnterpriseBean that handles user applications.
 * @author kim
 */
@Stateless
public class ApplicationEJB {
    
    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private LogManager logManager;
    
    void setEm(EntityManager em)
    {
        this.em = em;
    }
    
    /**
     * Method to place an application.
     * @param app data-transfer-object of application details.
     * @throws NoSuchAlgorithmException thrown when the encryption phase is invalid.
     */
    public void placeApplication(ApplicationDTO app) throws NoSuchAlgorithmException{
        em.persist(new Application(app));
    }
    
    /**
     * getPerson
     * @param username username of the person in question.
     * @return Person
     */
    public Person getPerson(String username){
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        try{
            return query.getSingleResult();
        }catch (NoResultException | NonUniqueResultException e) {
            logManager.log("GET PERSON REQUEST FOR NON-EXISTING USERNAME", Level.WARNING);
            return null;
        }
    }
    /**
     * getExpertise
     * @param expertise string that depicts the name of the expertise
     * @return Expertise
     */
    public Expertise getExpertise(String expertise){
        TypedQuery<Expertise> query = em.createNamedQuery("Expertise.findByName", Expertise.class);
        query.setParameter("expertise", expertise);
        try{
            return query.getSingleResult();
        }catch (NoResultException | NonUniqueResultException e) {
            logManager.log("GET EXPERTISE REQUEST FOR NON-EXISTING NAME", Level.WARNING);
            return null;
        }
    }
    
    /**
     * getExpertiseList
     * @return list of expertises that exists in the database
     */
    public List<Expertise> getExpertiseList()
    {
        Query query = em.createQuery("SELECT e from Expertise e");
        return (List<Expertise>) query.getResultList();
    }

    /**
     * getApplicationList
     * @return list of applications that exists in the database
     */
    public List<Application> getApplicationList(){
        Query query = em.createQuery("SELECT e from Application e");
        return (List<Application>) query.getResultList();
    }

    /**
     * getApplicationList
     * @param username username of the person that are fetching the applications
     * @return list of all applications made by a particular user.
     */
    public List<Application> getApplicationList(String username){
        TypedQuery<Application> query = em.createNamedQuery("Application.findByUser", Application.class);        
        query.setParameter("person", getPerson(username));        
        return (List<Application>) query.getResultList();
    }

    /**
     * Sets the logManager
     * @param logManager logmanager that is used to log application exceptions
     */
    public void setLogManager(LogManager logManager)
    {
        this.logManager = logManager;
    }
}
