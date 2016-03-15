/*
 * Classname: RegisterEJBTest
 * Version: 0.1
 * Date: 20-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains tests for the RegisterEJB class.
 * @author kim
 */
public class RegisterEJBTest 
{
    private RegisterEJB instance;
        
    /**
     * Class constructor
     */
    public RegisterEJBTest() 
    {
    } 
    
    /**
     * This method is called before the tests are executed
     */
    @Before
    public void setUp() 
    {
        instance = new RegisterEJB();
    }
    
    /**
     * This methods is called after the tests have finished
     */
    @After
    public void tearDown() 
    {
        instance = null;
    }

    /**
     * Test of validateRegistration method, of class RegisterEJB.
     * @throws java.lang.Exception
     */
    @Test
    public void testValidateRegistration() throws Exception 
    {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Person> mockQuery = mock(TypedQuery.class);
        List mockList = mock(List.class);
        when((mockList.isEmpty())).thenReturn((true));
        when((mockManager.createNamedQuery("Person.findByUserName", Person.class))).thenReturn((mockQuery));
        when((mockQuery.getResultList())).thenReturn((mockList));
        when((mockQuery.setParameter("username", "test"))).thenReturn(mockQuery);
        instance.setEm(mockManager);
        assertTrue(instance.validateRegistration("test"));
        when((mockList.isEmpty())).thenReturn((false));
        assertFalse(instance.validateRegistration("test"));
    }
    
    @Test
    public void testRegister() throws Exception{
        EntityManager mockManager = mock(EntityManager.class);
        PersonDTO person = mock(PersonDTO.class);
        when((person.getPassword())).thenReturn("test");
        instance.setEm(mockManager);
        instance.register(person);
    }
}
