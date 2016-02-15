/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
*/
package grupp14.IV1201.entities;

import grupp14.IV1201.DTO.PersonDTO;
import grupp14.IV1201.model.SHA512;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author marcelmattsson
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Person.findByUserName",
            query="SELECT p FROM Person p WHERE p.username = :username"),
})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private String surname;
    private String ssn;
    private String mail;
    @Column(unique = true)
    private String username;
    private String password;
    private String roll_id;
    
    public Person(){}
    
    public Person(PersonDTO personInfo) throws NoSuchAlgorithmException{
        
        this.mail = personInfo.getMail();
        this.name = personInfo.getName();
        this.password = new SHA512().encrypt(personInfo.getPassword());
        this.roll_id = personInfo.getRoll_id();
        this.ssn = personInfo.getSsn();
        this.surname = personInfo.getSurname();
        this.username = personInfo.getUsername();
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "grupp14.IV1201.model.User[ id=" + id + " ]";
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getSsn() {
        return ssn;
    }
    
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRoll_id() {
        return roll_id;
    }
    
    public void setRoll_id(String roll_id) {
        this.roll_id = roll_id;
    }
    
}
