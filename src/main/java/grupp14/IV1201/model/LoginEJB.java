/* 
 * Classname: LoginEJB
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */
package grupp14.IV1201.model;

import grupp14.IV1201.entities.Person;
import grupp14.IV1201.util.LogManager;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

/**
 * EnterpriseBean that handles login-transactions.
 * @author marcelmattsson
 */
@Stateless
public class LoginEJB
{
    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private final SHA512 sha = new SHA512();
    private LogManager logManager;

    void setEm(EntityManager em) 
    {
        this.em = em;
    }        
    /**
     * Validates login from user.
     * 
     * Uses the entity manager to call the .createNamedQuery method to find a person by username.
     * Fetches the password if the user is found in the DB and decrypts the encrypted
     * password in order to check if the @param password is the same. If the user is found and the
     * password matches it will return true. If it catches an exception it will return false.
     * 
     *

     * @param username username of the login-request
     * @param password password of the login request
     * @return true if successful login, otherwise false.
     * @throws java.security.NoSuchAlgorithmException thrown when the encryption phase is invalid.
     * @throws NoResultException thrown when no user was found with the specified username
     * @throws NonUniqueResultException thrown when multiple users with the same username was found.
     */
    public boolean validateLogin(@Size(min=3, max=16) String username,
            @Size(min=6, max=30) String password)
            throws NoSuchAlgorithmException
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        try {
            Person p = query.getSingleResult();
            return p.getPassword().equals(sha.encrypt(password));
        } catch (NoResultException | NonUniqueResultException e) {
            logManager.log("LOGIN VALIDATION FAILED", Level.WARNING);
            return false;
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
    public String getRole(@Size(min=3, max=16) String username)
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        try {
            Person p = query.getSingleResult();
            return p.getRoll_id();
        } catch (NoResultException | NonUniqueResultException e) {
            logManager.log("GET ROLE REQUEST FOR NON-EXISTING USERNAME", Level.WARNING);
            return "";
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
