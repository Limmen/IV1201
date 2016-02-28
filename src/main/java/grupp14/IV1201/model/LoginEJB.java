/* 
 * Classname: LoginEJB
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */
package grupp14.IV1201.model;

import grupp14.IV1201.entities.Person;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

/**
 *
 * @author kim
 */
@Stateless
public class LoginEJB
{
    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private final SHA512 sha = new SHA512();

    void setEm(EntityManager em) 
    {
        this.em = em;
    }        
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
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
            return false;
        }
    }

    /**
     *
     * @param username
     * @return
     */
    public String getRole(@Size(min=3, max=16) String username)
    {
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        try {
            Person p = query.getSingleResult();
            return p.getRoll_id();
        } catch (NoResultException | NonUniqueResultException e) {
            return "";
        }
    }
}
