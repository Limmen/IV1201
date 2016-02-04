/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author kim
 */
public class PasswordTest {
    
    public PasswordTest() {
    }
    
    @Test
    public void generateTestPasswords() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update("admin".getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);
            Assert.assertEquals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",output);
            System.out.println(output);
            
            md.update("wl9nk23a".getBytes("UTF-8")); // Change this to "UTF-16" if needed
            digest = md.digest();
            bigInt = new BigInteger(1, digest);
            output = bigInt.toString(16);
            System.out.println(output);
            
            md.update("test".getBytes("UTF-8")); // Change this to "UTF-16" if needed
            digest = md.digest();
            bigInt = new BigInteger(1, digest);
            output = bigInt.toString(16);
            System.out.println(output);
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
