/* 
 * Classname: RegisterEJB
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.entities.Person;
import grupp14.IV1201.util.LogManager;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * EnterpriseBean that handles registration transactions.
 * @author marcelmattsson
 */
@Stateless
public class RegisterEJB
{
    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private LogManager logManager;

    void setEm(EntityManager em) 
    {
        this.em = em;
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
    public void register(@NotNull PersonDTO p) throws NoSuchAlgorithmException
    {
        em.persist(new Person(p));
    }

    /**
     * Validates a registration.
     * 
     * Uses the entity manager to call the .createNamedQuery to find a person by username in the 
     * DB. If the username does not exist in the DB it will return true. Otherwise it will return
     * false
     * 
     * @param username username of the person to lookup
     * @return true if the username exists, otherwise false.
     */
    public boolean validateRegistration(@Size(min=3, max=16) String username)
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        return query.getResultList().isEmpty(); //check if username is taken.
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
