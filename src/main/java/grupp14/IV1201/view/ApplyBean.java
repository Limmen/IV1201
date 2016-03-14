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
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
     * @throws IOException thrown when the specified URL cannot be found for the redirection
     * @throws NoSuchAlgorithmException thrown when the encryption phase in the model was invalid.
     */
    public void apply() throws NoSuchAlgorithmException, IOException
    {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String username = contr.getUsername();
        if(username != null){
            Person person = contr.getPerson(username);
            Expertise exp = contr.getExpertise(expertise);
            if(person == null || exp == null)
                externalContext.redirect(externalContext.getRequestContextPath()
                        + "/applicant/applicationerror.xhtml");
            else{
                ApplicationDTO app = new ApplicationDTO(years,person, exp,
                        new java.sql.Date(availableFrom.getTime()),
                        new java.sql.Date(availableTo.getTime()));
                try{
                    contr.apply(app);
                    externalContext.redirect(externalContext.getRequestContextPath()
                            + "/applicant/applicationsuccess.xhtml");
                }catch(Exception e){
                    externalContext.redirect(externalContext.getRequestContextPath()
                            + "/applicant/applicationerror.xhtml");
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
     * getExpertiseList
     * @return list of expertises
     */
    public List<String> getExpertiseList()
    {
        return expertiseList;
    }
    
    /**
     * getExpertise
     * @return the selected expertise
     */
    public String getExpertise()
    {
        return expertise;
    }
    
    /**
     * updates the selected expertise
     * @param expertise  the new selected expertise
     */
    public void setExpertise(String expertise)
    {
        this.expertise = expertise;
    }
    
    /**
     * getAvailableFrom date
     * @return date from when the applicant is available
     */
    public Date getAvailableFrom()
    {
        return availableFrom;
    }
    
    /**
     * Updates the avilable from date
     * @param AvailableFrom date from when the applicant is available
     */
    public void setAvailableFrom(Date AvailableFrom)
    {
        this.availableFrom = AvailableFrom;
    }
    
    /**
     * getAvailableTo date
     * @return date to when the applicant is available
     */
    public Date getAvailableTo()
    {
        return availableTo;
    }
    
    /**
     * Updates the available to date
     * @param availableTo date to when the applicant is available
     */
    public void setAvailableTo(Date availableTo)
    {
        this.availableTo = availableTo;
    }
    
    /**
     * getyears
     * @return years of experience
     */
    public float getYears()
    {
        return years;
    }
    
    /**
     * Updates years of experience
     * @param years years of experience.
     */
    public void setYears(float years)
    {
        this.years = years;
    }
}
