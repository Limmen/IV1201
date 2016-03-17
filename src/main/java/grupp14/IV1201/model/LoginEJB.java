/* 
 * Classname: LoginEJB
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */
package grupp14.IV1201.model;

import grupp14.IV1201.integration.DAO.DataAccessObject;
import grupp14.IV1201.util.LogManager;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.Size;

/**
 * EnterpriseBean that handles login-transactions.
 * @author marcelmattsson, alexander
 */
@Stateless
public class LoginEJB
{
    @EJB
    private DataAccessObject dao;
    private final SHA512 sha = new SHA512();
    private LogManager logManager;
   
    void setDao(DataAccessObject dao)
    {
        this.dao = dao;
    }
    
    /**
     * Validates login from user.
     * 
     * Uses the DataAccessObject to validate login credentials. If the given username and password
     * matches a pair of username and password in the database that means the login is valid.
     * To compare passwords it's neccessary to encrypt the given password and compare it with the
     * hash value that is stored in the database.
     * 
     * @param username username of the login-request
     * @param password password of the login request
     * @return true if successful login, otherwise false.
     * @throws java.security.NoSuchAlgorithmException thrown when the encryption phase is invalid.
     */
    public boolean validateLogin(@Size(min=3, max=16) String username,
            @Size(min=6, max=30) String password)
            throws NoSuchAlgorithmException
    {
        String pw = dao.getPersonPassword(username);
        if(pw == null)
            return false;
        else
            return dao.getPersonPassword(username).equals(sha.encrypt(password));
    }
    
    /**
     * Sets the logManager
     * @param logManager logmanager that is used to log application exceptions
     */
    public void setLogManager(LogManager logManager)
    {
        this.logManager = logManager;
    }
}
