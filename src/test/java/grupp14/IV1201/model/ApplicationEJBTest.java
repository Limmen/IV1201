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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author kim
 */
public class ApplicationEJBTest {
    private ApplicationEJB instance;
    public ApplicationEJBTest() {
    }

    @Before
    public void setUp() {
        instance = new ApplicationEJB();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of placeApplication method, of class ApplicationEJB.
     */
    @Test
    public void testPlaceApplication() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        ApplicationDTO app = mock(ApplicationDTO.class);
        instance.setEm(mockManager);
        instance.placeApplication(app);
    }

    /**
     * Test of getPerson method, of class ApplicationEJB.
     */
    @Test
    public void testGetPerson() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Person> mockQuery = mock(TypedQuery.class);
        Person mockPerson = mock(Person.class);
        when((mockManager.createNamedQuery("Person.findByUserName", Person.class))).thenReturn((mockQuery));
        when((mockQuery.getSingleResult())).thenReturn((mockPerson));
        when((mockQuery.setParameter("username", "test"))).thenReturn(mockQuery);
        instance.setEm(mockManager);
        Assert.assertEquals(mockPerson, instance.getPerson("test"));
        when((mockQuery.getSingleResult())).thenThrow(new NoResultException());
        Assert.assertEquals(null, instance.getPerson("test"));
    }
    
    /**
     * Test of getExpertise method, of class ApplicationEJB.
     */
    @Test
    public void testGetExpertise() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Expertise> mockQuery = mock(TypedQuery.class);
        Expertise mockExpertise = mock(Expertise.class);
        when((mockManager.createNamedQuery("Expertise.findByName", Expertise.class))).thenReturn((mockQuery));
        when((mockQuery.getSingleResult())).thenReturn((mockExpertise));
        when((mockQuery.setParameter("expertise", "test"))).thenReturn(mockQuery);
        instance.setEm(mockManager);
        Assert.assertEquals(mockExpertise, instance.getExpertise("test"));
        when((mockQuery.getSingleResult())).thenThrow(new NoResultException());
        Assert.assertEquals(null, instance.getExpertise("test"));
    }

    /**
     * Test of getExpertiseList method, of class ApplicationEJB.
     */
    @Test
    public void testGetExpertiseList() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        Query mockQuery = mock(Query.class);
        List<Expertise> mockList = mock(List.class);
        when((mockManager.createQuery("SELECT e from Expertise e"))).thenReturn((mockQuery));
        when((mockQuery.getResultList())).thenReturn((mockList));       
        instance.setEm(mockManager);
        Assert.assertEquals(mockList, instance.getExpertiseList());
    }

    /**
     * Test of getApplicationList method, of class ApplicationEJB.
     */
    @Test
    public void testGetApplicationList() throws Exception {
        EntityManager mockManager = mock(EntityManager.class);
        Query mockQuery = mock(Query.class);
        List<Application> mockList = mock(List.class);
        when((mockManager.createQuery("SELECT e from Application e"))).thenReturn((mockQuery));
        when((mockQuery.getResultList())).thenReturn((mockList));       
        instance.setEm(mockManager);
        Assert.assertEquals(mockList, instance.getApplicationList());               
    }
    
}
