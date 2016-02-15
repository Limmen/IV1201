/*
 * Classname: ApplyBean
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.view;

import grupp14.IV1201.controller.ControllerEJB;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Future;

/**
 *
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
    @DecimalMax(value="100")
    private float years;

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        /*
        expertiseList = new ArrayList();
        expertiseList.add("Please select");
        for(String s : contr.getExpertiseList()){
        expertiseList.add(s);
        }*/
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

    /**
     *
     */
    public void apply()
    {
        String username = contr.getUsername();
        if(username != null)
            contr.apply(expertise, years, availableFrom, availableTo, username);
        clear();
    }

    /**
     *
     */
    public void clear()
    {
        expertise = "";
        availableFrom = null;
        availableTo = null;
        years = 0;
    }
    
}
