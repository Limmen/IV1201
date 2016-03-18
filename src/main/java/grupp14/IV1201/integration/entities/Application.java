/*
* Classname: Application
* Version: 0.1
* Date: 15-2-2016
* Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
*/

package grupp14.IV1201.integration.entities;

import grupp14.IV1201.DTO.ApplicationDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity that represents a Application.
 * @author marcelmattsson, alexander
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Application.findByUser",
            query="SELECT p FROM Application p WHERE p.person = :person"),
})
public class Application implements Serializable 
{
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
     * Class constructor
     */
    public Application(){}
    
    /**
     * Class constructor
     * @param applikationInfo DataTransferObject of application parameters
     */
    public Application(ApplicationDTO applikationInfo)
    {
        this.yearsOfExperience = applikationInfo.getYearsOfExperience();
        this.person = applikationInfo.getPerson();
        this.expertise = applikationInfo.getExpertise();
        this.dateFrom = applikationInfo.getDateFrom();
        this.dateTo = applikationInfo.getDateTo();
    }
    
    /**
     * getSerialVersionUid
     * @return serialVersionUID
     */
    public static long getSerialVersionUID() 
    {
        return serialVersionUID;
    }
    
    /**
     * getId
     * @return Id of the application
     */
    public BigInteger getId() 
    {
        return id;
    }
    
    /**
     * getYearsOfExperience
     * @return years of experience of the application
     */
    public float getYearsOfExperience() 
    {
        return yearsOfExperience;
    }

    /**
     * Updates the years of experience of the application
     * @param yearsOfExperience float
     */
    public void setYearsOfExperience(float yearsOfExperience) 
    {
        this.yearsOfExperience = yearsOfExperience;
    }        
    
    /**
     * getPerson
     * @return person that made the application
     */
    public Person getPerson() 
    {
        return person;
    }

    /**
     * Updates the person that made the application
     * @param person Person
     */
    public void setPerson(Person person) 
    {
        this.person = person;
    }        
    
    /**
     * getExpertise
     * @return expertise of the application
     */
    public Expertise getExpertise() 
    {
        return expertise;
    }

    /**
     * Updates the expertise profile of the application
     * @param expertise
     */
    public void setExpertise(Expertise expertise) 
    {
        this.expertise = expertise;
    }        
    
    /**
     * getDateFrom
     * @return date from when the person is available
     */
    public Date getDateFrom() 
    {
        return dateFrom;
    }

    /**
     * Updates the date from when the person is available of the application
     * @param dateFrom Date
     */
    public void setDateFrom(Date dateFrom) 
    {
        this.dateFrom = dateFrom;
    }
        
    /**
     * getDateTo
     * @return date to when the person is available
     */
    public Date getDateTo() 
    {
        return dateTo;
    }

    /**
     * Updates the date to when the person is available of the application
     * @param dateTo Date
     */
    public void setDateTo(Date dateTo) 
    {
        this.dateTo = dateTo;
    }
        
    /**
     * Generates and returns a hashcode
     * @return integer hashcode
     */
    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * equals method, used to compare if two applications are equal
     * @param object object to compare to
     * @return true if equals otherwise false
     */
    @Override
    public boolean equals(Object object) 
    {
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
     * toString.
     * @return  a string representation of the person
     */
    @Override
    public String toString() 
    {
        return "grupp14.IV1201.entities.Application[ id=" + id + " ]";
    }
    
}
