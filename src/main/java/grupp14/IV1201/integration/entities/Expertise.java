/* 
 * Classname: Expertise
 * Version: 0.1
 * Date: 16-3-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.integration.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity that represents a Expertise.
 * @author marcelmattsson, alexander
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Expertise.findByName",
            query="SELECT p FROM Expertise p WHERE p.expertise = :expertise"),
})
public class Expertise implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String expertise;

    /**
     * Class constructor
     */
    public Expertise() {}
    
    /**
     * Class constructor
     * @param expertis name of the expertise
     */
    public Expertise(String expertis)
    {
        this.expertise = expertis;
    }

    /**
     * getExpertise
     * @return name of the expertise
     */
    public String getExpertise() 
    {
        return expertise;
    }

    /**
     * Updates the expertise name
     * @param expertise string
     */
    public void setExpertise(String expertise) 
    {
        this.expertise = expertise;
    }
    
    /**
     * getId
     * @return id of the expertise
     */
    public BigInteger getId() 
    {
        return id;
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
     * equals method, used to compare if two expertise are equal
     * @param object object to compare to
     * @return true if equals otherwise false.
     */
    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expertise)) {
            return false;
        }
        Expertise other = (Expertise) object;
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
        return "grupp14.IV1201.entities.Expertis[ id=" + id + " ]";
    }
    
}
