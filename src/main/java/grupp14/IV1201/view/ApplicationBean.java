/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.view;

import com.lowagie.text.DocumentException;
import grupp14.IV1201.DTO.ApplicationViewDTO;
import grupp14.IV1201.controller.ControllerEJB;
import grupp14.IV1201.entities.Application;
import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;
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
    private List<ApplicationViewDTO> applications;
    private ApplicationViewDTO selectedApplication;
    
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
            for(Application app : contr.getApplicationList(username)){
                Person p = contr.getPerson(username);
                Expertise e = contr.getExpertise(app.getExpertisID());
                applications.add(new ApplicationViewDTO(app,p,e));
            }
        }
        else{
            for(Application app : contr.getApplicationList()){
                Person p = contr.getPerson(app.getPersonID());
                Expertise e = contr.getExpertise(app.getExpertisID());
                applications.add(new ApplicationViewDTO(app,p,e));
            }
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
     * @return a list of all applications from the given ApplicationBean
     */
    public List<ApplicationViewDTO> getApplications() {
        return applications;
    }

    /**
     *
     * @return a selected application from the given ApplicationBean
     */
    public ApplicationViewDTO getSelectedApplication() {
        return selectedApplication;
    }

    /**
     *
     * @param selectedApplication a selected application
     */
    public void setSelectedApplication(ApplicationViewDTO selectedApplication) {
        this.selectedApplication = selectedApplication;
    }
        
    
}
