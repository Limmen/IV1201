/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.view;

import com.lowagie.text.DocumentException;
import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.entities.Application;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 * Managed bean representing the interface between the model and the
 * applications-page.
 * @author kim
 */
@Named(value = "applicationBean")
@ViewScoped
public class ApplicationBean implements Serializable {
    @EJB
    private ControllerEJB contr;
    private List<Application> applications;
    private Application selectedApplication;
    
    /**
     * 
     */
    @PostConstruct
    public void init(){
        fetchApplications();        
        if(applications == null)
            applications = new ArrayList();        
    }
    
    /**
     * 
     */
    public void fetchApplications(){
        applications = new ArrayList();
        String username = contr.getUsername();
        if(contr.getRole(username).equals("applicant")){            
            applications = contr.getApplicationList(username);
        }
        else{
            applications = contr.getApplicationList();
        }
    }

    /**
     * Calls the controller to produce a PDF-file of the selected application
     * @throws IOException
     * @throws DocumentException
     */
    public void createPDF() throws IOException, DocumentException{
        contr.createPDF(selectedApplication);
    }

    /**
     *
     * @return
     */
    public List<Application> getApplications() {
        return applications;
    }

    /**
     *
     * @return
     */
    public Application getSelectedApplication() {
        return selectedApplication;
    }

    /**
     *
     * @param selectedApplication
     */
    public void setSelectedApplication(Application selectedApplication) {
        this.selectedApplication = selectedApplication;
    }
        
    
}
