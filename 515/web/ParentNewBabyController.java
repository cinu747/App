/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import edu.iit.sat.itmd4515.ckolathabraham.domain.BabyGender;
import edu.iit.sat.itmd4515.ckolathabraham.service.ParentService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * <!--        //Followed some of the intructor example and designed own code//-->
 * @author Cini
 */
@Named
@RequestScoped
public class ParentNewBabyController {

    private static final Logger LOG = Logger.getLogger(ParentNewBabyController.class.getName());
    @EJB
    private ParentService parentSvc;
    @Inject
    ParentWelcomeController control;
    @Inject
    FacesContext facesContext;
    private boolean showFacesMessage = false;

    private Baby baby;

    /**
     *
     */
    public ParentNewBabyController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("BabyController.postConstruct");
        baby = new Baby();
    }

    //helper method
    /**
     *
     * @return
     */
    public BabyGender[] getBabyGender() {
        return BabyGender.values();
    }

    //action methods
    //read-only view inorder to display
    /**
     *
     * @param b
     * @return
     */
    public String displayReadBabyPage(Baby b) {
        LOG.info("Inside displayReadBabyPage with " + this.baby.toString());
        this.baby = b;
        return "/parent/readBaby.xhtml";
    }

    //helps in updating the page
    /**
     *
     * @param b
     * @return
     */
    public String displayUpdateBabyPage(Baby b) {
        LOG.info("Inside displayUpdateBabyPage with " + this.baby.toString());
        this.baby = b;
        return "/parent/updateBaby.xhtml";
    }

    //asks the user for confirmation
    /**
     *
     * @param b
     * @return
     */
    public String displayDeleteBabyPage(Baby b) {
        LOG.info("Inside displayDeleteBabyPage with " + this.baby.toString());
        this.baby = b;
        return "/parent/deleteBaby.xhtml";
    }

    //insert action method
    /**
     *
     * @return
     */
    public String executeCreateButtonClick() {
        LOG.info("Inside executeCreateButtonClick with " + this.baby.toString());

        //refresh the collection
        //control.getParent().addBaby(baby);
        parentSvc.createBabyForParent(baby, control.getParent());
        control.refreshParent();
        facesContext.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Success!",
                "New baby record " + this.baby.getFirstName() + " was created successfully"));
        this.showFacesMessage = true;
        return "/parent/welcome.xhtml";
    }

    //insert action method
    /**
     *
     * @return
     */
    public String executeUpdateButtonClick() {
        LOG.info("Inside executeUpdateButtonClick with " + this.baby.toString());

        parentSvc.updateBabyForParent(baby);
        return "/parent/welcome.xhtml?faces-redirect=true";
    }

    //insert action method
    /**
     *
     * @return
     */
    public String executeDeleteButtonClick() {
        LOG.info("Inside executeDeleteButtonClick with " + this.baby.toString());
        parentSvc.deleteBabyForParent(baby);
        return "/parent/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public Baby getBaby() {
        return baby;
    }

    /**
     *
     * @param baby
     */
    public void setBaby(Baby baby) {
        this.baby = baby;
    }

    /**
     *
     * @return
     */
    public boolean isShowFacesMessage() {
        return showFacesMessage;
    }

    /**
     *
     * @param showFacesMessage
     */
    public void setShowFacesMessage(boolean showFacesMessage) {
        this.showFacesMessage = showFacesMessage;
    }
}
