/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.model;

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
 *
 * @author kim
 */
public class RegisterEJBTest {
    RegisterEJB instance;
    public RegisterEJBTest() {
    }       
    @Before
    public void setUp() {
        instance = new RegisterEJB();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of validateRegistration method, of class RegisterEJB.
     */
    @Test
    public void testValidateRegistration() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Person> mockQuery = mock(TypedQuery.class);
        List mockList = mock(List.class);
        when((mockList.isEmpty())).thenReturn((true));
        when((mockManager.createNamedQuery("Person.findByUserName", Person.class))).thenReturn((mockQuery));
        when((mockQuery.getResultList())).thenReturn((mockList));
        when((mockQuery.setParameter("username", "test"))).thenReturn(mockQuery);
        
        assertTrue(instance.validateRegistration(mockManager, "test"));
        when((mockList.isEmpty())).thenReturn((false));
        assertFalse(instance.validateRegistration(mockManager, "test"));
    }
    
}
