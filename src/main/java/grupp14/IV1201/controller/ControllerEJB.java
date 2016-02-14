/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.controller;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.model.HttpSessionBean;
import grupp14.IV1201.model.LoginEJB;
import grupp14.IV1201.model.RegisterEJB;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kim
 */
@Stateless
public class ControllerEJB {

    @PersistenceContext(unitName = "grupp14_IV1201_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @EJB
    LoginEJB login;
    @EJB
    RegisterEJB register;
 
    private final HttpSessionBean session = new HttpSessionBean();
    
    public void registerUser(PersonDTO person) throws NoSuchAlgorithmException{
        register.register(entityManager, person);
    }
    public void unRegisterUser(String username){
        
    }
    public boolean validateLogin(String username, String password) throws NoSuchAlgorithmException{
       return login.validateLogin(entityManager, username, password);
    }
    public String getRole(String username){
        return login.getRole(entityManager, username);
    }
    public boolean validateRegistration(String username){
        return register.validateRegistration(entityManager, username);
    }
    public HttpSession getSession(){
        return session.getSession();
    }
    public String getUsername(){
        return session.getUserName();
    }
    public List<String> getExpertiseList(){
        return null;
    }
    public void apply(String expertise, float years, Date from, Date to, String username){
        
    }

}
