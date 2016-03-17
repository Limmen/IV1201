/* 
 * Classname: RegisterEJB
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.model;

import grupp14.IV1201.integration.DAO.DataAccessObject;
import grupp14.IV1201.util.LogManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.Size;

/**
 * EnterpriseBean that handles registration transactions.
 * @author marcelmattsson, alexander
 */
@Stateless
public class RegisterEJB
{
    @EJB
    private DataAccessObject dao;
    private LogManager logManager;
     
    
    void setDao(DataAccessObject dao)
    {
        this.dao = dao;
    }
        
    /**
     * Validates a registration.
     * 
     * Uses the DataAccessObject to check if the given username is available or already taken.
     * 
     * @param username username of the person to lookup
     * @return true if the username exists, otherwise false.
     */
    public boolean validateRegistration(@Size(min=3, max=16) String username)
    {
        return dao.getPersonByUsername(username) == null;
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
