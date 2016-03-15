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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author marcelmattsson, alexander
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Application.findByUser",
            query="SELECT p FROM Application p WHERE p.person = :person"),
})
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private float yearsOfExperience;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Expertise expertise;
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
            this.person = applikationInfo.getPerson();
            this.expertise = applikationInfo.getExpertise();
            this.dateFrom = applikationInfo.getDateFrom();
            this.dateTo = applikationInfo.getDateTo();
        }

    /**
     *
     * @return
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
     * @return
     */
    public float getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     *
     * @return
     */
    public Person getPerson() {
        return person;
    }

    /**
     *
     * @return
     */
    public Expertise getExpertise() {
        return expertise;
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
