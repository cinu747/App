/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Parent;
import edu.iit.sat.itmd4515.ckolathabraham.service.ParentService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Cini
 */
@Named
@RequestScoped
public class ParentWelcomeController {

    private static final Logger LOG = Logger.getLogger(ParentWelcomeController.class.getName());

    //Model - Authenticated user
    private Parent parent;

    @Inject
    LoginController loginController;
    @EJB
    ParentService parentSvc;

    /**
     *
     */
    public ParentWelcomeController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside ParentWelcomeController.postConstruct");
        parent = parentSvc.findByUsername(loginController.getAuthenticatedUser());
        LOG.info("Leaving ParentWelcomeController.postConstruct with " + parent.toString());
    }

    //utility method
    /**
     *
     */
    public void refreshParent() {
        parent = parentSvc.findByUsername(loginController.getAuthenticatedUser());
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
