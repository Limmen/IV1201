/* 
 * Classname: Person
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.entities;

import grupp14.IV1201.DTO.ApplikationDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author marcelmattsson
 */
@Entity
public class Applikation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String erfarenhet;
    private BigInteger personID;
    private BigInteger expertisID;
    private java.sql.Date dateFrom;
    private java.sql.Date dateTo;

    /**
     *
     * @param applikationInfo
     * @throws NoSuchAlgorithmException
     */
        public Applikation(ApplikationDTO applikationInfo) throws NoSuchAlgorithmException
        {
            this.erfarenhet = applikationInfo.getErfarenhet();
            this.personID = applikationInfo.getPersonID();
            this.expertisID = applikationInfo.getExpertisID();
            this.dateFrom = applikationInfo.getDateFrom();
            this.dateTo = applikationInfo.getDateTo();
        }
    
    
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applikation)) {
            return false;
        }
        Applikation other = (Applikation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupp14.IV1201.entities.Erfarenhet[ id=" + id + " ]";
    }
    
}
