/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.model;

import grupp14.IV1201.entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author kim
 */
public class LoginEJBTest {
    LoginEJB instance;
    public LoginEJBTest() {
    }       
    @Before
    public void setUp() {
        instance = new LoginEJB();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of validateLogin method, of class LoginEJB.
     */
    @Test
    public void testValidateLogin() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Person> mockQuery = mock(TypedQuery.class);
        Person mockPerson = mock(Person.class);
        when((mockPerson.getPassword())).thenReturn(("125d6d03b32c84d492747f79cf0bf6e179d287f341384eb5d6d3197525ad6be8e6df0116032935698f99a09e265073d1d6c32c274591bf1d0a20ad67cba921bc"));
        when((mockManager.createNamedQuery("Person.findByUserName", Person.class))).thenReturn((mockQuery));
        when((mockQuery.getSingleResult())).thenReturn((mockPerson));
        when((mockQuery.setParameter("username", "test"))).thenReturn(mockQuery);
        
        assertTrue(instance.validateLogin(mockManager, "test", "testtest"));
    }

    /**
     * Test of getRole method, of class LoginEJB.
     */
    @Test
    public void testGetRole() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Person> mockQuery = mock(TypedQuery.class);
        Person mockPerson = mock(Person.class);
        when((mockPerson.getRoll_id())).thenReturn(("applicant"));
        when((mockManager.createNamedQuery("Person.findByUserName", Person.class))).thenReturn((mockQuery));
        when((mockQuery.getSingleResult())).thenReturn((mockPerson));
        when((mockQuery.setParameter("username", "test"))).thenReturn(mockQuery);
               
        Assert.assertEquals("applicant", instance.getRole(mockManager, "test"));
        when((mockPerson.getRoll_id())).thenReturn(("recruit"));
        Assert.assertEquals("recruit", instance.getRole(mockManager, "test"));
    }
    
}
