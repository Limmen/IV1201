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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.IOException
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
     *
     * @return
     */
    public List<String> getExpertiseList()
    {
        return expertiseList;
    }
    
    /**
     *
     * @param expertiseList
     */
    public void setExpertiseList(List<String> expertiseList)
    {
        this.expertiseList = expertiseList;
    }
    
    /**
     *
     * @return
     */
    public String getExpertise()
    {
        return expertise;
    }
    
    /**
     *
     * @param expertise
     */
    public void setExpertise(String expertise)
    {
        this.expertise = expertise;
    }
    
    /**
     *
     * @return
     */
    public Date getAvailableFrom()
    {
        return availableFrom;
    }
    
    /**
     *
     * @param AvailableFrom
     */
    public void setAvailableFrom(Date AvailableFrom)
    {
        this.availableFrom = AvailableFrom;
    }
    
    /**
     *
     * @return
     */
    public Date getAvailableTo()
    {
        return availableTo;
    }
    
    /**
     *
     * @param availableTo
     */
    public void setAvailableTo(Date availableTo)
    {
        this.availableTo = availableTo;
    }
    
    /**
     *
     * @return
     */
    public float getYears()
    {
        return years;
    }
    
    /**
     *
     * @param years
     */
    public void setYears(float years)
    {
        this.years = years;
    }
}
