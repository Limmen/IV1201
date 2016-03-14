/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.DTO;

import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;
import java.sql.Date;

/**
 *
 * @author marcelmattsson
 */
public class ApplicationDTO {
    private final float yearsOfExperience;
    private final Person person;
    private final Expertise expertise;
    private final java.sql.Date dateFrom;
    private final java.sql.Date dateTo;    
    
    /**
     *
     * @param yearsOfExperience
     * @param person
     * @param dateFrom
     * @param expertise
     * @param dateTo
     */
    public ApplicationDTO(float yearsOfExperience, Person person, Expertise expertise, 
            java.sql.Date dateFrom, java.sql.Date dateTo)
    {
        this.yearsOfExperience = yearsOfExperience;
        this.person = person;
        this.expertise = expertise;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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

}
