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
import java.io.Serializable;
import java.math.BigInteger;
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
    private Date availableFrom;
    @Future(message="We're only interested in applications from possible"
            + " future employees")
    private Date availableTo;
    @DecimalMax(value="200")
    private float years;

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
     */
    public void apply() throws NoSuchAlgorithmException
    {
        String username = contr.getUsername();        
        if(username != null){
            BigInteger personId = contr.getUserId(username);
            BigInteger expertiseId = contr.getExpertiseId(expertise);
            ApplicationDTO app = new ApplicationDTO(years,personId, expertiseId,
                    new java.sql.Date(availableFrom.getTime()),
                    new java.sql.Date(availableTo.getTime()));
            contr.apply(app);
        }        
        clear();
    }

    /**
     * This method clears the filled in fields.
     */
    public void clear()
    {
        expertise = "";
        availableFrom = null;
        availableTo = null;
        years = 0;
    }
    
    /**
     * 
     * @return a list of expertises from the given ApplyBean
     */
    public List<String> getExpertiseList()
    {
        return expertiseList;
    }
    
    /**
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
     */
    public String getExpertise()
    {
        return expertise;
    }
    
    /**
     *
     * @param expertise a expertise
     */
    public void setExpertise(String expertise)
    {
        this.expertise = expertise;
    }
    
    /**
     *
     * @return a availabeFrom Date from the given ApplyBean
     */
    public Date getAvailableFrom()
    {
        return availableFrom;
    }
    
    /**
     *
     * @param AvailableFrom a availableFrom Date 
     */
    public void setAvailableFrom(Date availableFrom)
    {
        this.availableFrom = availableFrom;
    }
    
    /**
     *
     * @return availabeTo Date from the given ApplyBean
     */
    public Date getAvailableTo()
    {
        return availableTo;
    }
    
    /**
     *
     * @param availableTo a availableTo Date 
     */
    public void setAvailableTo(Date availableTo)
    {
        this.availableTo = availableTo;
    }
    
    /**
     *
     * @return years from the given ApplyBean
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
