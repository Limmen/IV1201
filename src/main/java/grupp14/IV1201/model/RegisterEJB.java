/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.model;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.entities.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author kim
 */
@Stateless
public class RegisterEJB {
    
    public void register(EntityManager em, PersonDTO p){
        em.persist(new Person(p));
    }
    public boolean validateRegistration(EntityManager em, String username){
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);
        return query.getResultList().isEmpty();
    }
}
