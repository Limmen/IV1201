/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.DTO;

import grupp14.IV1201.entities.Application;
import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;

/**
 *
 * @author kim
 */
public class ApplicationViewDTO {
    
    private Application app;
    private Person person;
    private Expertise expertise;
    
    /**
     *
     * @param app
     * @param person
     * @param expertise
     */
    public ApplicationViewDTO(Application app, Person person, Expertise expertise){
        this.app = app;
        this.person = person;
        this.expertise = expertise;
    }

    /**
     *
     * @return
     */
    public Application getApp() {
        return app;
    }

    /**
     *
     * @param app
     */
    public void setApp(Application app) {
        this.app = app;
    }

    /**
     *
     * @return
     */
    public Person getPerson() {
        return person;
    }  

    /**
     *
     * @return
     */
    public Expertise getExpertise() {
        return expertise;
    }

}
