/* 
 * Classname: SHA512
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that handles hashing of passwords.
 * @author kim
 */
public class SHA512
{

    /**
     * Generates a hash of a specified password-string.
     *
     * @param pw password-string
     * @return hash-value
     * @throws NoSuchAlgorithmException This exception is thrown when a particular cryptographic
     * algorithm is requested but is not available in the environment.
     */
    public String encrypt(String pw) throws NoSuchAlgorithmException
    {
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
