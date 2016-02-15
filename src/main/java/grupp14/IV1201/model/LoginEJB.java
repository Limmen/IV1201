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
import javax.persistence.TypedQuery;

/**
 *
 * @author kim
 */
@Stateless
public class LoginEJB
{

    private final SHA512 sha = new SHA512();

    /**
     *
     * @param em
     * @param username
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public boolean validateLogin(EntityManager em, String username, String password)
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
     * @param em
     * @param username
     * @return
     */
    public String getRole(EntityManager em, String username)
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
