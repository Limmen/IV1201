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
public class ApplikationDTO {
    private final String erfarenhet;
    private final BigInteger personID;
    private final BigInteger expertisID;
    private final java.sql.Date dateFrom;
    private final java.sql.Date dateTo;
    
        /**
     *
     * @param erfarenhet
     * @param personID
     * @param expertisID
     * @param dateFrom
     * @param dateTo
     */
    public ApplikationDTO(String erfarenhet, BigInteger personID, BigInteger expertisID, 
            java.sql.Date dateFrom, java.sql.Date dateTo)
    {
        this.erfarenhet = erfarenhet;
        this.personID = personID;
        this.expertisID = expertisID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    
    /**
     *
     * @return
     */
    public String getErfarenhet() {
        return erfarenhet;
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
