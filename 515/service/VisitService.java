/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.service;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import edu.iit.sat.itmd4515.ckolathabraham.domain.DeliveryVisit;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Hospital;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Parent;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Initializing a class called visit service
 *
 * @author Cini
 */
@Named
@Stateless
public class VisitService extends AbstractService<DeliveryVisit> {

    /**
     *
     */
    public VisitService() {
        super(DeliveryVisit.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<DeliveryVisit> findAll() {
        return em.createNamedQuery("DeliveryVisit.findAll", DeliveryVisit.class).getResultList();

    }

    /**
     *
     * @param visit
     */
    public void scheduleVisit(DeliveryVisit visit) {
        DeliveryVisit newVisit = new DeliveryVisit();
        newVisit.setDateofvisit(visit.getDateofvisit());
        newVisit.setTimeofvisit(visit.getTimeofvisit());
        newVisit.setVisitType(visit.getVisitType());
        Parent parentRef = em.getReference(Parent.class, visit.getParent().getId());
        Baby babyRef = em.getReference(Baby.class, visit.getBaby().getId());
        Hospital hospitalRef = em.getReference(Hospital.class, visit.getHospital().getId());
        newVisit.scheduleVisit(parentRef, babyRef, hospitalRef);
        em.persist(newVisit);

    }

    public void cancelVisit(DeliveryVisit visit) {
        visit = em.getReference(DeliveryVisit.class, visit.getId());
        //manage all the relationships
        visit.deleteVisit();
        em.remove(visit);
    }

    public void changeVisit(DeliveryVisit visit) {
        DeliveryVisit managedVisit = em.getReference(DeliveryVisit.class, visit.getId());
        //user on the form can change the hospital, visit type, time and date
        managedVisit.setDateofvisit(visit.getDateofvisit());
        managedVisit.setTimeofvisit(visit.getTimeofvisit());
        managedVisit.setVisitType(visit.getVisitType());
        managedVisit.deleteVisit();
        managedVisit.scheduleVisit(
                em.getReference(Parent.class, visit.getParent().getId()),
                em.getReference(Baby.class, visit.getBaby().getId()),
                em.getReference(Hospital.class, visit.getHospital().getId()));
        em.persist(managedVisit);
    }
}
