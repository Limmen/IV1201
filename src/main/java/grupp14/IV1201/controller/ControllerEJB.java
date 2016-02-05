/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.controller;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.model.LoginEJB;
import grupp14.IV1201.model.Person;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kim
 */
@Stateless
public class ControllerEJB {

    @EJB
    LoginEJB login;
    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    //Controller methods
    
    public void RegUser(PersonDTO person){
        entityManager.persist(new Person(person));
    }
}
