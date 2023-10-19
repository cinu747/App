/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Parent;
import edu.iit.sat.itmd4515.ckolathabraham.security.User;
import edu.iit.sat.itmd4515.ckolathabraham.security.UserService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Cini
 */
@Named
@RequestScoped
public class NewParentUserSignupController {

    private static final Logger LOG = Logger.getLogger(NewParentUserSignupController.class.getName());
    //model
    private Parent parent;

    @EJB
    UserService userSvc;

    /**
     *
     */
    public NewParentUserSignupController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the NewParentUserSignupController postConstruct()");
        //initialize our model so that it is never null
        parent = new Parent();
        //initialize and hence associating the user inside the parent
        parent.setUser(new User());

    }

    //action methods
    /**
     *
     * @return
     */
    public String executeNewParentUserSignupClick() {
        LOG.info(this.parent.toString());

        userSvc.signupNewParentUser(this.parent);
        return "login.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public Parent getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

}
