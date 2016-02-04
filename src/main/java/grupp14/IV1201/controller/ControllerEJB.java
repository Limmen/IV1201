/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.controller;

import grupp14.IV1201.model.LoginEJB;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kim
 */
@Stateless
public class ControllerEJB {

    @EJB
    LoginEJB login;
    //Controller methods
}
