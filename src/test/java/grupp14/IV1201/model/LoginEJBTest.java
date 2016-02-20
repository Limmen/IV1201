/*
 * Classname: LoginEJBTest
 * Version: 0.1
 * Date: 20-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import grupp14.IV1201.entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains test cases for the LoginEJB class.
 * @author kim
 */
public class LoginEJBTest 
{
    private LoginEJB instance;
    
    /**
     * Class constructor
     */
    public LoginEJBTest() {
    }       

    /**
     * This method is called before the tests are executed
     */
    @Before
    public void setUp() {
        instance = new LoginEJB();
    }
    
    /**
     * This methods is called after the tests have finished
     */
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of validateLogin method, of class LoginEJB.
     * @throws java.lang.Exception
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
        
        //assertTrue(instance.validateLogin(mockManager, "test", "testtest"));
    }

    /**
     * Test of getRole method, of class LoginEJB.
     * @throws java.lang.Exception
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
        instance.setEm(mockManager);
        Assert.assertEquals("applicant", instance.getRole("test"));
        when((mockPerson.getRoll_id())).thenReturn(("recruit"));
        Assert.assertEquals("recruit", instance.getRole("test"));
    }
    
}
