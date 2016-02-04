/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.view;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author kim
 */
public class UserinfoBeanTest {
    UserinfoBean userInfo;
    public UserinfoBeanTest() {
    }
    @Before
    public void setUp() {
        userInfo = new UserinfoBean();
    }
    @After
    public void tearDown() {
        userInfo = null;
    }
    /**
     * Test of init method, of class UserinfoBean.
     */
    @Test
    public void testInit() {
        ExternalContext mockContext = mock(ExternalContext.class);
        when((mockContext.getRemoteUser())).thenReturn(("testUser"));
        when((mockContext.isUserInRole("recruit"))).thenReturn((true));
        when((mockContext.isUserInRole("applicant"))).thenReturn((false));
        userInfo.updateUserInfo(mockContext);
        System.out.println("Username: " + userInfo.getUsername());
        Assert.assertEquals("testUser",userInfo.getUsername());
        Assert.assertEquals("recruit",userInfo.getRole());
        
        when((mockContext.isUserInRole("recruit"))).thenReturn((false));
        when((mockContext.isUserInRole("applicant"))).thenReturn((true));
        userInfo.updateUserInfo(mockContext);
        Assert.assertEquals("applicant",userInfo.getRole());
    }        
}
