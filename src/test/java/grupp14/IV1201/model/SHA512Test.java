/*
 * Classname: SHA512Test
 * Version: 0.1
 * Date: 20-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains test-cases for the SHA512 class.
 * @author kim
 */
public class SHA512Test 
{
    private SHA512 sha;
    
    /**
     * Class constructor
     */
    public SHA512Test() 
    {
    }
    
    /**
     * This method is called before the tests are executed
     */
    @Before
    public void setUp() 
    {
        sha = new SHA512();
    }
    
    /**
     * This methods is called after the tests have finished
     */
    @After
    public void tearDown() {
        sha = null;
    }
    
    /**
     * Test of encrypt method, of class SHA512.
     */
    @Test
    public void testEncrypt()
    {
        boolean exception = false;
        try{
            Assert.assertEquals("ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff",sha.encrypt("test"));
            Assert.assertEquals("da51b44001979df36941316003333e3a1ed07cc7fe5dd073a7a7d3b13e18c88d674dcb2dfad383a0693bffdf90d5dbbd98a686c6793f9449e9bcfc8d57471c31",sha.encrypt("lindberg"));
            Assert.assertEquals("ba2e6a898a2879d9657571e2de442f3ad49314e9cbcb90fec1b23cc00ac0acdd66388c087c69ee24be7760fe77037985795e0deb725a193cda907db4dbebc16a",sha.encrypt("superbowl"));
        }catch(Exception e){
            exception = true;
        }
        assertFalse(exception);
    }
    
}
