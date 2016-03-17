/* 
 * Classname: DataAccessObject
 * Version: 0.1
 * Date: 17-3-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */
package grupp14.IV1201.integration.DAO;

import grupp14.IV1201.DTO.ApplicationDTO;
import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.integration.entities.Application;
import grupp14.IV1201.integration.entities.Expertise;
import grupp14.IV1201.integration.entities.Person;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DataAccessObject. This class is responsible to get data from the specified data source.
 * @author Kim Hammar
 */
@Stateless
public class DataAccessObject
{

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
    public void placeApplication(ApplicationDTO app) throws NoSuchAlgorithmException
    {
        em.persist(new Application(app));
    }

    /**
     * Adds a new user(Person) to the DB.
     * 
     * Uses entity manager to call the .persist method to add a new user to the DB with the help of
     * a PersonDTO.
     * 
     * @param p data-transfer-object of Person details.
     * @throws java.security.NoSuchAlgorithmException thrown when the encryption phase is invalid.
     * 
     */
    public void registerPerson(@NotNull PersonDTO p) throws NoSuchAlgorithmException
    {
        em.persist(new Person(p));
    }

   /**
     * getPersonByUsername
     * @param username username of the person in question.
     * @return Person
     */
    public Person getPersonByUsername(String username){
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
     * getExpertiseByName
     * @param expertise string that depicts the name of the expertise
     * @return Expertise
     */
    public Expertise getExpertiseByName(String expertise){
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
    public List<Application> getApplicationList()
    {
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
        query.setParameter("person", getPersonByUsername(username));        
        return (List<Application>) query.getResultList();
    }
    
    /**
     * Get password (hash) of a specific person
     * @param username
     * @return String hash
     */
    public String getPersonPassword(String username) 
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        try {
            Person p = query.getSingleResult();
            return p.getPassword();
        } catch (NoResultException | NonUniqueResultException e) {
            logManager.log("LOGIN VALIDATION FAILED", Level.WARNING);
            return null;
        }
    }

    /**
     * Finds the role of a given person.
     * 
     * Uses the entity manager to call the .createNamedQuery method to find a person by username. 
     * If the user is found it returns its role. If an exception is caught it will return false.
     * 
     * @param username username of the person in question
     * @return Role of the person
     * @throws NoResultException thrown when no user was found with the specified username
     * @throws NonUniqueResultException thrown when multiple users with the same username was found.
     */
    public String getPersonRole(@Size(min=3, max=16) String username)
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        try {
            Person p = query.getSingleResult();
            return p.getRoll_id();
        } catch (NoResultException | NonUniqueResultException e) {
            logManager.log("GET ROLE REQUEST FOR NON-EXISTING USERNAME", Level.WARNING);
            return null;
        }
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
