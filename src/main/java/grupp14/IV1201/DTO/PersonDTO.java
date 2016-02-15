/* 
 * Classname: PersonDTO
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.DTO;

/**
 *
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
     *
     * @param name
     * @param surname
     * @param ssn
     * @param mail
     * @param username
     * @param password
     * @param roll_id
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
     *
     * @return
     */
    public String getName() 
    {
        return name;
    }

    /**
     *
     * @return
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     *
     * @return
     */
    public String getSsn() 
    {
        return ssn;
    }

    /**
     *
     * @return
     */
    public String getMail()
    {
        return mail;
    }

    /**
     *
     * @return
     */
    public String getUsername()
    {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     *
     * @return
     */
    public String getRoll_id() 
    {
        return roll_id;
    }
    
}
