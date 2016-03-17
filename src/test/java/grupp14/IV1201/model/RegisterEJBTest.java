/*
 * Classname: RegisterEJBTest
 * Version: 0.1
 * Date: 20-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import grupp14.IV1201.integration.DAO.DataAccessObject;
import grupp14.IV1201.integration.entities.Person;
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
        DataAccessObject dao = mock(DataAccessObject.class);
        Person mockPerson = mock(Person.class);
        when((dao.getPersonByUsername("test"))).thenReturn((mockPerson));        
        instance.setDao(dao);
        assertFalse(instance.validateRegistration("test"));
        when((dao.getPersonByUsername("test"))).thenReturn((null));
        assertTrue(instance.validateRegistration("test"));
    }

}
