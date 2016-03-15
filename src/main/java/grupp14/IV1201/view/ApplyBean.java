/*
* Classname: ApplyBean
* Version: 0.1
* Date: 15-2-2016
* Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
*/

package grupp14.IV1201.view;

import grupp14.IV1201.DTO.ApplicationDTO;
import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Future;

/**
 * ManagedBean representing the interface between the model and the
 * apply-page.
 * @author kim
 */

@Named(value = "applyBean")
@SessionScoped
public class ApplyBean implements Serializable
{
    @EJB
    ControllerEJB contr;
    private List<String> expertiseList;
    private String expertise;
    private Date availableFrom = new Date();
    @Future(message="We're only interested in applications from possible"
            + " future employees")
    private Date availableTo = new Date();
    @DecimalMax(value="200")
    private float years = 0;
    private boolean applicationSuccess = false;
    private boolean applicationFailed = false;
    
    
    /**
     * This method is called by the cdi-container after dependency-injection
     * but before the class is put into service.
     */
    @PostConstruct
    public void init()
    {
        expertiseList = new ArrayList();
        expertiseList.add("Please select");
        for(Expertise s : contr.getExpertiseList()){
            expertiseList.add(s.getExpertise());
        }
    }
    
    /**
     * This method is called when the user clicks the "apply" button
     * on the apply-page.
     *
     * The method will call the controller to place an application.
     * @throws NoSuchAlgorithmException thrown when the encryption phase in the model was invalid.
     */
    public void apply() throws NoSuchAlgorithmException
    {
        String username = contr.getUsername();
        if(username != null){
            Person person = contr.getPerson(username);
            Expertise exp = contr.getExpertise(expertise);
            if(person == null || exp == null){
                applicationFailed = true;
                applicationSuccess = false;
            }
            
            else{
                ApplicationDTO app = new ApplicationDTO(years,person, exp,
                        new java.sql.Date(availableFrom.getTime()),
                        new java.sql.Date(availableTo.getTime()));
                try{
                    contr.apply(app);
                    applicationSuccess = true;
                    applicationFailed = false;
                }catch(Exception e){
                    applicationFailed = true;
                    applicationSuccess = false;
                }
            }
        }
        clear();
    }
    
    /**
     * This method clears the filled in fields.
     */
    public void clear()
    {
        expertise = "";
        availableFrom = new Date();
        availableTo = new Date();
        years = 0;
    }
    
    /**
<<<<<<< HEAD
     * 
     * @return a list of expertises from the given ApplyBean
=======
     * getExpertiseList
     * @return list of expertises
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public List<String> getExpertiseList()
    {
        return expertiseList;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param expertiseList a list of expertises
     */
    public void setExpertiseList(List<String> expertiseList)
    {
        this.expertiseList = expertiseList;
    }
    
    /**
     *
     * @return a expertise from the given ApplyBean
=======
     * getExpertise
     * @return the selected expertise
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public String getExpertise()
    {
        return expertise;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param expertise a expertise
=======
     * updates the selected expertise
     * @param expertise  the new selected expertise
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public void setExpertise(String expertise)
    {
        this.expertise = expertise;
    }
    
    /**
<<<<<<< HEAD
     *
     * @return a availabeFrom Date from the given ApplyBean
=======
     * getAvailableFrom date
     * @return date from when the applicant is available
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public Date getAvailableFrom()
    {
        return availableFrom;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param AvailableFrom a availableFrom Date 
=======
     * Updates the avilable from date
     * @param AvailableFrom date from when the applicant is available
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public void setAvailableFrom(Date availableFrom)
    {
        this.availableFrom = availableFrom;
    }
    
    /**
<<<<<<< HEAD
     *
     * @return availabeTo Date from the given ApplyBean
=======
     * getAvailableTo date
     * @return date to when the applicant is available
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public Date getAvailableTo()
    {
        return availableTo;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param availableTo a availableTo Date 
=======
     * Updates the available to date
     * @param availableTo date to when the applicant is available
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public void setAvailableTo(Date availableTo)
    {
        this.availableTo = availableTo;
    }
    
    /**
<<<<<<< HEAD
     *
     * @return years from the given ApplyBean
=======
     * getyears
     * @return years of experience
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public float getYears()
    {
        return years;
    }
    
    /**
<<<<<<< HEAD
     *
     * @param years 
=======
     * Updates years of experience
     * @param years years of experience.
>>>>>>> 34132b8421f587c1a511107cfb11d6721cb7462c
     */
    public void setYears(float years)
    {
        this.years = years;
    }

    /**
     * isApplicationSuccess
     * @return boolean wether the application was successful
     */
    public boolean isApplicationSuccess() {
        return applicationSuccess;
    }

    /**
     * isApplicationFailed
     * @return boolean wether the application failed
     */
    public boolean isApplicationFailed() {
        return applicationFailed;
    }
    
    
}
