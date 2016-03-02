/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.view;

import com.lowagie.text.DocumentException;
import grupp14.IV1201.controller.ControllerEJB;
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
    private List<String> applications;
    private String selectedApplication;
    
    /**
     * 
     */
    @PostConstruct
    public void init(){
        applications = new ArrayList();
        applications.add("Application 1");
        applications.add("Application 2");
        applications.add("Application 3");
    }
    
    /**
     * 
     */
    public void fetchApplications(){
        String username = contr.getUsername();
        if(contr.getRole(username).equals("applicant")){
            //Fetch applications for username
        }
        else{
            //Fetch ALL applications
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
    public List<String> getApplications() {
        return applications;
    }

    /**
     *
     * @return
     */
    public String getSelectedApplication() {
        return selectedApplication;
    }

    /**
     *
     * @param selectedApplication
     */
    public void setSelectedApplication(String selectedApplication) {
        this.selectedApplication = selectedApplication;
    }
        
    
}
