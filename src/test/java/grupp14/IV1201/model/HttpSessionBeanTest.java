/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.model;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class HttpSessionBeanTest {
    HttpSessionBean instance;
    FacesContext mockContext;
    ExternalContext mockExternalContext;
    public HttpSessionBeanTest() {
    }

    @Before
    public void setUp() {
        mockContext = mock(FacesContext.class);
        mockExternalContext = mock(ExternalContext.class);
        when((mockContext.getExternalContext())).thenReturn((mockExternalContext));
        instance = new HttpSessionBean();
        instance = new HttpSessionBean() {
            @Override
              FacesContext getFacesContext() {
                  return mockContext;
              }
        };
    }
    
    @After
    public void tearDown() {
        instance = null;
    }


    /**
     * Test of getSession method, of class HttpSessionBean.
     */
    @Test
    public void testGetSession() {
        HttpSession mockSession = mock(HttpSession.class);
        when((mockExternalContext.getSession(false))).thenReturn((null));
        Assert.assertEquals(null, instance.getSession());
        when((mockExternalContext.getSession(false))).thenReturn((mockSession));
        Assert.assertEquals(mockSession, instance.getSession());
    }

    /**
     * Test of getRequest method, of class HttpSessionBean.
     */
    @Test
    public void testGetRequest() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when((mockExternalContext.getRequest())).thenReturn((null));
        Assert.assertEquals(null, instance.getRequest());
        when((mockExternalContext.getRequest())).thenReturn((mockRequest));
        Assert.assertEquals(mockRequest, instance.getRequest());
    }

    /**
     * Test of getUserName method, of class HttpSessionBean.
     */
    @Test
    public void testGetUserName() {
        HttpSession mockSession = mock(HttpSession.class);
        when((mockExternalContext.getSession(false))).thenReturn((mockSession));
        when((mockSession.getAttribute("username"))).thenReturn(("root"));
        Assert.assertEquals("root", instance.getUserName());
        
    }
    
}
