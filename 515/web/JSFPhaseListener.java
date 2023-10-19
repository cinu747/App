/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Cini
 */
public class JSFPhaseListener implements PhaseListener {

    private static final Logger LOG = Logger.getLogger(JSFPhaseListener.class.getName());

    /**
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     *
     * @param event
     */
    @Override
    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RESTORE_VIEW) {
            LOG.info("********************************** NEW JSF REQUEST STARTING **********************************");
        }
        LOG.info("BEFORE JSF PHASE ********************************** " + event.getPhaseId().toString());
    }

    /**
     *
     * @param event
     */
    @Override
    public void afterPhase(PhaseEvent event) {
        LOG.info("AFTER JSF PHASE ********************************** " + event.getPhaseId().toString());
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            LOG.info("********************************** JSF REQUEST COMPLETED **********************************");
        }
    }

}
