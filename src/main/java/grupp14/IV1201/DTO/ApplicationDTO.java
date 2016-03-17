/* 
 * Classname: ApplicationDTO
 * Version: 0.1
 * Date: 16-3-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.DTO;

import grupp14.IV1201.integration.entities.Expertise;
import grupp14.IV1201.integration.entities.Person;
import java.sql.Date;

/**
 * DataTransferObject for a application
 * @author marcelmattsson
 */
public class ApplicationDTO 
{
    private final float yearsOfExperience;
    private final Person person;
    private final Expertise expertise;
    private final java.sql.Date dateFrom;
    private final java.sql.Date dateTo;    
    
    /**
     * Class constructor
     * @param yearsOfExperience years of experience
     * @param person Person that made the application
     * @param dateFrom date from when the person is available
     * @param expertise expertise profile of the application
     * @param dateTo date to when the person is available
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
     * getYearsOfExperience
     * @return years of experience of the application
     */
    public float getYearsOfExperience() 
    {
        return yearsOfExperience;
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
     * getExpertise
     * @return expertise if the application
     */
    public Expertise getExpertise() 
    {
        return expertise;
    }

    /**
     * getDateFrom
     * @return date from when the person is available of the application
     */
    public Date getDateFrom() 
    {
        return dateFrom;
    }

    /**
     * getDateTo
     * @return date to when the person is available of the application
     */
    public Date getDateTo() 
    {
        return dateTo;
    }

}
