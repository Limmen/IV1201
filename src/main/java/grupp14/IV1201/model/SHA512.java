/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author kim
 */
public class SHA512 {
    public String encrypt(String pw) throws NoSuchAlgorithmException{
        String encryptedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(pw.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        encryptedPassword = sb.toString();        
        return encryptedPassword;
    }
}
