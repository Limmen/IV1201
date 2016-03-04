/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.DTO;

import java.math.BigInteger;
import java.sql.Date;

/**
 *
 * @author marcelmattsson
 */
public class ApplicationDTO {
    private final float yearsOfExperience;
    private final BigInteger personID;
    private final BigInteger expertisID;
    private final java.sql.Date dateFrom;
    private final java.sql.Date dateTo;    
    
    /**
     *
     * @param yearsOfExperience
     * @param personID
     * @param expertisID
     * @param dateFrom
     * @param dateTo
     */
    public ApplicationDTO(float yearsOfExperience, BigInteger personID, BigInteger expertisID, 
            java.sql.Date dateFrom, java.sql.Date dateTo)
    {
        this.yearsOfExperience = yearsOfExperience;
        this.personID = personID;
        this.expertisID = expertisID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    
    /**
     *
     * @return
     */
    public float getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     *
     * @return
     */
    public BigInteger getPersonID() {
        return personID;
    }

    /**
     *
     * @return
     */
    public BigInteger getExpertisID() {
        return expertisID;
    }

    /**
     *
     * @return
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     *
     * @return
     */
    public Date getDateTo() {
        return dateTo;
    }
    
}
