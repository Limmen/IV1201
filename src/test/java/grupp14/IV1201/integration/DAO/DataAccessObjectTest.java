/*
 * Classname: DataAccessObjectTest
 * Version: 0.1
 * Date: 17-3-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */
package grupp14.IV1201.integration.DAO;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.integration.entities.Application;
import grupp14.IV1201.integration.entities.Expertise;
import grupp14.IV1201.integration.entities.Person;
import grupp14.IV1201.util.LogManager;
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
 * @author Kim Hammar
 */
public class DataAccessObjectTest 
{
    DataAccessObject instance;
    public DataAccessObjectTest() 
    {
    }
    
    @Before
    public void setUp() 
    {
        instance = new DataAccessObject();
    }
    
    @After
    public void tearDown() 
    {
        instance = null;
    }

   /**
     * Test of getPerson method, of class DataAccessObject.
     */
    @Test
    public void testGetPersonByUsername() throws Exception 
    {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Person> mockQuery = mock(TypedQuery.class);
        LogManager mockLogManager = mock(LogManager.class);
        Person mockPerson = mock(Person.class);
        when((mockManager.createNamedQuery("Person.findByUserName", Person.class))).thenReturn((mockQuery));
        when((mockQuery.getSingleResult())).thenReturn((mockPerson));
        when((mockQuery.setParameter("username", "test"))).thenReturn(mockQuery);
        instance.setEm(mockManager);
        instance.setLogManager(mockLogManager);
        Assert.assertEquals(mockPerson, instance.getPersonByUsername("test"));
        when((mockQuery.getSingleResult())).thenThrow(new NoResultException());
        Assert.assertEquals(null, instance.getPersonByUsername("test"));
    }
    
    /**
     * Test of getExpertise method, of class DataAccessObject.
     */
    @Test
    public void testGetExpertiseByName() throws Exception 
    {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Expertise> mockQuery = mock(TypedQuery.class);
        Expertise mockExpertise = mock(Expertise.class);
        LogManager mockLogManager = mock(LogManager.class);
        when((mockManager.createNamedQuery("Expertise.findByName", Expertise.class))).thenReturn((mockQuery));
        when((mockQuery.getSingleResult())).thenReturn((mockExpertise));
        when((mockQuery.setParameter("expertise", "test"))).thenReturn(mockQuery);
        instance.setEm(mockManager);
        instance.setLogManager(mockLogManager);
        Assert.assertEquals(mockExpertise, instance.getExpertiseByName("test"));
        when((mockQuery.getSingleResult())).thenThrow(new NoResultException());
        Assert.assertEquals(null, instance.getExpertiseByName("test"));
    }

    /**
     * Test of getExpertiseList method, of class DataAccessObject.
     */
    @Test
    public void testGetExpertiseList() throws Exception 
    {
        EntityManager mockManager = mock(EntityManager.class);
        Query mockQuery = mock(Query.class);
        List<Expertise> mockList = mock(List.class);
        when((mockManager.createQuery("SELECT e from Expertise e"))).thenReturn((mockQuery));
        when((mockQuery.getResultList())).thenReturn((mockList));       
        instance.setEm(mockManager);
        Assert.assertEquals(mockList, instance.getExpertiseList());
    }

    /**
     * Test of getApplicationList method, of class DataAccessObject.
     */
    @Test
    public void testGetApplicationList() throws Exception 
    {
        EntityManager mockManager = mock(EntityManager.class);
        Query mockQuery = mock(Query.class);
        List<Application> mockList = mock(List.class);
        when((mockManager.createQuery("SELECT e from Application e"))).thenReturn((mockQuery));
        when((mockQuery.getResultList())).thenReturn((mockList));       
        instance.setEm(mockManager);
        Assert.assertEquals(mockList, instance.getApplicationList());               
    }
    /**
     * Test of getRole method, of class DataAccessObject.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPersonRole() throws Exception 
    {
        EntityManager mockManager = mock(EntityManager.class);
        TypedQuery<Person> mockQuery = mock(TypedQuery.class);
        LogManager mockLogManager = mock(LogManager.class);
        Person mockPerson = mock(Person.class);
        when((mockPerson.getRoll_id())).thenReturn(("applicant"));
        when((mockManager.createNamedQuery("Person.findByUserName", Person.class))).thenReturn((mockQuery));
        when((mockQuery.getSingleResult())).thenReturn((mockPerson));
        when((mockQuery.setParameter("username", "test"))).thenReturn(mockQuery);
        instance.setEm(mockManager);
        instance.setLogManager(mockLogManager);
        Assert.assertEquals("applicant", instance.getPersonRole("test"));
        when((mockPerson.getRoll_id())).thenReturn(("recruit"));
        Assert.assertEquals("recruit", instance.getPersonRole("test"));
        when((mockQuery.getSingleResult())).thenThrow(new NoResultException());
        Assert.assertEquals(null, instance.getPersonRole("test"));
    }
    
        
    /**
     * Test of register method of class DataAccessObject
     * @throws Exception 
     */
    @Test
    public void testRegister() throws Exception
    {
        EntityManager mockManager = mock(EntityManager.class);
        PersonDTO person = mock(PersonDTO.class);
        when((person.getPassword())).thenReturn("test");
        instance.setEm(mockManager);
        instance.registerPerson(person);
    }
}
