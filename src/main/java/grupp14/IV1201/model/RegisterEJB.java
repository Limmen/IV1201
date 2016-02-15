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
import javax.persistence.TypedQuery;


@Stateless
public class RegisterEJB
{
    
    /**
     *
     * @param em
     * @param p
     * @throws NoSuchAlgorithmException
     */
    
    public void register(EntityManager em, PersonDTO p) throws NoSuchAlgorithmException
    {
        em.persist(new Person(p));
    }

    /**
     *
     * @param em
     * @param username
     * @return
     */
    public boolean validateRegistration(EntityManager em, String username)
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        return query.getResultList().isEmpty();
    }
}
