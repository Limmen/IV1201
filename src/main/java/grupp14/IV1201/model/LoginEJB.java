/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.model;

import grupp14.IV1201.entities.Person;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author kim
 */
@Stateless
public class LoginEJB {

    SHA512 sha;
    
    @PostConstruct
    public void init(){
        sha = new SHA512();
    }
    
    public boolean validateLogin(EntityManager em, String username, String password){
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        Person p = query.getSingleResult();
        return p.getPassword().equals(sha.encrypt(password));
    }
}
