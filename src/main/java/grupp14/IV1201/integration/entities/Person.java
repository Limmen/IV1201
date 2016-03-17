/* 
 * Classname: Person
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.integration.entities;

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
 * Entity that represents a Person.
 * @author marcelmattsson, alexander
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Person.findByUserName",
            query="SELECT p FROM Person p WHERE p.username = :username"),
})
public class Person implements Serializable
{
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
    
    /**
     * Class constructor
     */
    public Person(){}
    
    /**
     * Class constructor
     * @param personInfo DataTransferObject of person information
     * @throws NoSuchAlgorithmException thrown when the encryption phase is invalid.
     */
    public Person(PersonDTO personInfo) throws NoSuchAlgorithmException
    {
        this.mail = personInfo.getMail();
        this.name = personInfo.getName();
        this.password = new SHA512().encrypt(personInfo.getPassword());
        this.roll_id = personInfo.getRoll_id();
        this.ssn = personInfo.getSsn();
        this.surname = personInfo.getSurname();
        this.username = personInfo.getUsername();
    }
    
    /**
     * getId
     * @return id of the person
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
     * equals method, used to compare if two persons are equal
     * @param object object to compare to
     * @return
     */
    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
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
        return "grupp14.IV1201.model.User[ id=" + id + " ]";
    }
    
    /**
     * getName
     * @return name of the person
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     * Updates the name of the person
     * @param name string
     */
    public void setName(String name) 
    {
        this.name = name;
    }
    
    /**
     * getSurname
     * @return surname of the person
     */
    public String getSurname() 
    {
        return surname;
    }
    
    /**
     * Updates the surname of the person
     * @param surname string
     */
    public void setSurname(String surname) 
    {
        this.surname = surname;
    }
    
    /**
     * getSsn
     * @return ssn of the person
     */
    public String getSsn() 
    {
        return ssn;
    }
    
    /**
     * Updates the ssn of the person
     * @param ssn string
     */
    public void setSsn(String ssn) 
    {
        this.ssn = ssn;
    }
    
    /**
     * getMail
     * @return mail of the person
     */
    public String getMail() 
    {
        return mail;
    }
    
    /**
     * Updates the mail of the person
     * @param mail string
     */
    public void setMail(String mail) 
    {
        this.mail = mail;
    }
    
    /**
     * getUsername
     * @return username of  the person
     */
    public String getUsername() 
    {
        return username;
    }
    
    /**
     * Updates the username of the person
     * @param username string
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }
    
    /**
     * getPassword
     * @return password of the person
     */
    public String getPassword() 
    {
        return password;
    }
    
    /**
     * Updates the password of the person
     * @param password string
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }
    
    /**
     * getRoll_id
     * @return roll_id of the perrson
     */
    public String getRoll_id() 
    {
        return roll_id;
    }
    
    /**
     * Updates the roll_id of the person
     * @param roll_id string
     */
    public void setRoll_id(String roll_id) 
    {
        this.roll_id = roll_id;
    }
    
}
