/* 
 * Classname: Person
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.entities;

import grupp14.IV1201.DTO.ApplicationDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author marcelmattsson, alexander
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Application.findByUserId",
            query="SELECT p FROM Application p WHERE p.personID = :id"),
})
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private float yearsOfExperience;
    private BigInteger personID;
    private BigInteger expertisID;
    private java.sql.Date dateFrom;
    private java.sql.Date dateTo;

    /**
     *
     */
    public Application(){}
    /**
     *
     * @param applikationInfo
     * @throws NoSuchAlgorithmException
     */
        public Application(ApplicationDTO applikationInfo) throws NoSuchAlgorithmException
        {
            this.yearsOfExperience = applikationInfo.getYearsOfExperience();
            this.personID = applikationInfo.getPersonID();
            this.expertisID = applikationInfo.getExpertisID();
            this.dateFrom = applikationInfo.getDateFrom();
            this.dateTo = applikationInfo.getDateTo();
        }
    
    /**
     *
     * @return
     */
    public BigInteger getId() {
        return id;
    }
    /**
     *
     * @param id
     */
    public void setId(BigInteger id) {
        this.id = id;
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
     * @param years
     */
    public void set(float years) {
        this.yearsOfExperience = years;
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
     * @param personID
     */
    public void setPersonID(BigInteger personID) {
        this.personID = personID;
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
     * @param expertisID
     */
    public void setExpertisID(BigInteger expertisID) {
        this.expertisID = expertisID;
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
     * @param dateFrom
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     *
     * @return
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     *
     * @param dateTo
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "grupp14.IV1201.entities.Erfarenhet[ id=" + id + " ]";
    }
    
}
