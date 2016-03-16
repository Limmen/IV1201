/* 
 * Classname: PersonDTO
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.DTO;

/**
 * DataTransferObject for a Person
 * @author marcelmattsson
 */
public class PersonDTO {
    private final String name;
    private final String surname;
    private final String ssn;
    private final String mail;
    private final String username;
    private final String password;
    private final String roll_id;
    
    /**
     * Class constructor
     * @param name name of the person
     * @param surname surname of the person
     * @param ssn ssn of the person
     * @param mail mail of the person
     * @param username username of the person
     * @param password password of the person
     * @param roll_id roll_id of the person
     */
    public PersonDTO(String name, String surname, String ssn, String mail, String username, 
            String password, String roll_id)
    {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.roll_id = roll_id;
        this.ssn = ssn;
        this.surname = surname;
        this.username = username;
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
     * getSurname
     * @return surname of the person
     */
    public String getSurname()
    {
        return surname;
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
     * getMail
     * @return mail of the person
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * getUsername
     * @return username of the person
     */
    public String getUsername()
    {
        return username;
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
     * getRoll_id
     * @return roll_id of the person
     */
    public String getRoll_id() 
    {
        return roll_id;
    }
    
}
