/*
* Classname: ApplicationBean
* Version: 0.1
* Date: 14-3-2016
* Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
*/ 

package grupp14.IV1201.view;

import com.lowagie.text.DocumentException;
import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.integration.entities.Application;
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
public class ApplicationBean implements Serializable 
{
    @EJB
    private ControllerEJB contr;
    private List<Application> applications;
    private Application selectedApplication;
    
    /**
     * This method is called by the cdi-container after dependency-injection
     * but before the class is put into service.
     */
    @PostConstruct
    public void init()
    {
        fetchApplications();        
        if(applications == null)
            applications = new ArrayList();        
    }
    
    /**
     * This method will call the controller to fetch applications from the database.
     * If it's a applicant requesting the application only the his/hers applications are retrieved.
     * If it's a recruiter then all applications are retrieved.
     */
    public void fetchApplications()
    {
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
     * @throws IOException IOException thrown when the specified URL cannot be found for the 
     * redirection.
     * @throws DocumentException Thrown when a error occurs in the creation of the pdf document.
     */
    public void createPDF() throws IOException, DocumentException
    {
        contr.createPDF(selectedApplication);
    }

    /**
     * getApplications
     * @return list of applications
     */
    public List<Application> getApplications() 
    {
        return applications;
    }

    /**
     * getSelectedApplication
     * @return the application that the user have selected currently
     */
    public Application getSelectedApplication() 
    {
        return selectedApplication;
    }

    /**
     * setSelectedApplication
     * @param selectedApplication updates the selected application.
     */
    public void setSelectedApplication(Application selectedApplication) 
    {
        this.selectedApplication = selectedApplication;
    }
        
    
}
