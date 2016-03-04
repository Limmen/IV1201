/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.model;

import grupp14.IV1201.DTO.ApplicationDTO;
import grupp14.IV1201.entities.Application;
import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author kim
 */
@Stateless
public class ApplicationEJB {
    
    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    void setEm(EntityManager em)
    {
        this.em = em;
    }
    
    public void placeApplication(ApplicationDTO app) throws NoSuchAlgorithmException{
        em.persist(new Application(app));
    }
    
    public BigInteger getUserId(String username){
        TypedQuery<Person> query = em.createNamedQuery("Person.findByUserName", Person.class);
        query.setParameter("username", username);        
        Person p = query.getSingleResult();
        return p.getId();        
    }
    
    public BigInteger getExpertiseId(String expertise){
        TypedQuery<Expertise> query = em.createNamedQuery("Expertise.findByName", Expertise.class);
        query.setParameter("expertise", expertise);        
        Expertise exp = query.getSingleResult();
        return exp.getId(); 
    }
    
    public List<Expertise> getExpertiseList()
    {
        Query query = em.createQuery("SELECT e from Expertise e");
        return (List<Expertise>) query.getResultList();
    }
    public List<Application> getApplicationList(){
        Query query = em.createQuery("SELECT e from Application e");
        return (List<Application>) query.getResultList();
    }
}
