/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name="Expertise.findByName",
            query="SELECT p FROM Expertise p WHERE p.expertise = :expertise"),
})
public class Expertise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String expertise;

    /**
     *
     */
    public Expertise() {}
    
    /**
     *
     * @param expertis
     */
    public Expertise(String expertis){
        this.expertise = expertis;
    }

    /**
     *
     * @return
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     *
     * @param expertise
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
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
     *
     * @return
     */
    @Override
    public String toString() {
        return "grupp14.IV1201.entities.Expertis[ id=" + id + " ]";
    }
    
}