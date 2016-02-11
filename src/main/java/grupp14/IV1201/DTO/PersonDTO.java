/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
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
    
    
    public PersonDTO(String name, String surname, String ssn, String mail, String username, String password, String roll_id)
    {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.roll_id = roll_id;
        this.ssn = ssn;
        this.surname = surname;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSsn() {
        return ssn;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoll_id() {
        return roll_id;
    }
    
}
