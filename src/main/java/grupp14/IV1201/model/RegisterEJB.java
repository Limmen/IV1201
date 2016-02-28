/* 
 * Classname: RegisterEJB
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.entities.Person;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kim
 */
@Stateless
public class RegisterEJB
{
    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    void setEm(EntityManager em) 
    {
        this.em = em;
    }
        
    /**
     *
     * @param p
     * @throws NoSuchAlgorithmException
     */
    
    public void register(@NotNull PersonDTO p) throws NoSuchAlgorithmException
    {
        em.persist(new Person(p));
    }

    /**
     *
     * @param username
     * @return
     */
    public boolean validateRegistration(@Size(min=3, max=16) String username)
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        return query.getResultList().isEmpty(); //check if username is taken.
    }
}
