/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import edu.iit.sat.itmd4515.ckolathabraham.domain.DeliveryVisit;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Hospital;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Parent;
import edu.iit.sat.itmd4515.ckolathabraham.service.BabyService;
import edu.iit.sat.itmd4515.ckolathabraham.service.VisitService;
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
public class ParentVisitController {

    private static final Logger LOG = Logger.getLogger(ParentVisitController.class.getName());

    private Baby baby;
    private DeliveryVisit visit;
    @Inject
    ParentWelcomeController parentWelcomeController;
    @EJB
    BabyService babySvc;
    @EJB
    VisitService visitSvc;

    /**
     *
     */
    public ParentVisitController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside ParentVisitController.postConstruct");
        visit = new DeliveryVisit();
        visit.setBaby(new Baby());
        visit.setParent(parentWelcomeController.getParent());
        visit.setHospital(new Hospital());
        LOG.info("Leaving ParentVisitController.postConstruct with " + visit.toString());
    }

    //init method for parameter style f:metadata
    /**
     *
     */
    public void initBabyById() {
        visit.setBaby(babySvc.read(this.visit.getBaby().getId()));
    }

    public void initVisitById() {
        LOG.info("initVisitById before find " + this.visit.toString());
        this.visit = visitSvc.read(visit.getId());
        LOG.info("initVisitById after find " + this.visit.toString());
    }

    //action method
    /**
     *
     * @param b
     * @return
     */
    public String displayVisitPage(Baby b) {
        LOG.info("Inside displayVisitPage " + b.toString());
        this.baby = b;
        visit.setBaby(baby);
        return "/parent/scheduleVisit.xhtml";
    }

    /**
     *
     * @return
     */
    public String executeScheduleButtonClick() {
        LOG.info("Inside executeScheduleButtonClick with " + this.visit.toString());
        LOG.info("Inside executeScheduleButtonClick with " + this.visit.getBaby().toString());
        LOG.info("Inside executeScheduleButtonClick with " + this.visit.getParent().toString());
        LOG.info("Inside executeScheduleButtonClick with " + this.visit.getHospital().toString());
        visitSvc.scheduleVisit(visit);
        parentWelcomeController.refreshParent();
        return "/parent/welcome.xhtml";
    }

    public String executeChangeButtonClick() {
        LOG.info("Inside executeChangeButtonClick with " + this.visit.toString());
        LOG.info("Inside executeChangeButtonClick with " + this.visit.getBaby().toString());
        LOG.info("Inside executeChangeButtonClick with " + this.visit.getParent().toString());
        LOG.info("Inside executeChangeButtonClick with " + this.visit.getHospital().toString());
        visitSvc.changeVisit(visit);
        parentWelcomeController.refreshParent();
        return "/parent/welcome.xhtml";
    }

    public String executeCancelButtonClick() {
        LOG.info("Inside executeCancelButtonClick with " + this.visit.toString());
        LOG.info("Inside executeCancelButtonClick with " + this.visit.getBaby().toString());
        LOG.info("Inside executeCancelButtonClick with " + this.visit.getParent().toString());
        LOG.info("Inside executeCancelButtonClick with " + this.visit.getHospital().toString());
        visitSvc.cancelVisit(visit);
        parentWelcomeController.refreshParent();
        return "/parent/welcome.xhtml";
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
    public DeliveryVisit getVisit() {
        return visit;
    }

    /**
     *
     * @param visit
     */
    public void setVisit(DeliveryVisit visit) {
        this.visit = visit;
    }

}
