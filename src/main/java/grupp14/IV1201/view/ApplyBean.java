/*
* Course project - IV1201 Design of Global Applications
* Royal Institute of Technology
* 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
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
public class ApplyBean implements Serializable {
    @EJB
    ControllerEJB contr;
    private List<String> expertiseList;
    private String expertise;    
    private Date availableFrom;
    @Future(message="We're only interested in applications from possible future employees")
    private Date availableTo;
    @DecimalMax(value="100")
    private float years;
    @PostConstruct
    public void init(){
        /*
        expertiseList = new ArrayList();
        expertiseList.add("Please select");
        for(String s : contr.getExpertiseList()){
        expertiseList.add(s);
        }*/
    }
    
    public List<String> getExpertiseList() {
        return expertiseList;
    }
    
    public void setExpertiseList(List<String> expertiseList) {
        this.expertiseList = expertiseList;
    }
    
    public String getExpertise() {
        return expertise;
    }
    
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    
    public Date getAvailableFrom() {
        return availableFrom;
    }
    
    public void setAvailableFrom(Date AvailableFrom) {
        this.availableFrom = AvailableFrom;
    }
    
    public Date getAvailableTo() {
        return availableTo;
    }
    
    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }
    
    public float getYears() {
        return years;
    }
    
    public void setYears(float years) {
        this.years = years;
    }
    public void apply(){
        String username = contr.getUsername();
        if(username != null)
            contr.apply(expertise, years, availableFrom, availableTo, username);
        clear();
    }
    public void clear(){
        expertise = "";
        availableFrom = null;
        availableTo = null;
        years = 0;
    }
    
}
