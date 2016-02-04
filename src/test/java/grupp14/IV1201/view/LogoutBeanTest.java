/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.view;

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
public class LogoutBeanTest {
    LogoutBean logout;
    public LogoutBeanTest() {
    }
    @Before
    public void setUp() {
        logout = new LogoutBean();
    }    
    @After
    public void tearDown() {
        logout = null;
    }

    /**
     * Test of logout method, of class LogoutBean.
     */
    @Test
    public void testLogout() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);
        when((mockRequest.getSession())).thenReturn((mockSession));
        Assert.assertEquals("/index?faces-redirect=true",logout.invalidateSession(mockRequest));
    }
    
}
