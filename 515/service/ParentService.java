/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.service;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import edu.iit.sat.itmd4515.ckolathabraham.domain.DeliveryVisit;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Parent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Cini
 */
@Stateless
public class ParentService extends AbstractService<Parent> {

    /**
     *
     */
    public ParentService() {
        super(Parent.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Parent> findAll() {
        return em.createNamedQuery("Parent.findAll", Parent.class).getResultList();

    }

    /**
     *
     * @param username
     * @return
     */
    public Parent findByUsername(String username) {
        return em
                .createNamedQuery("Parent.findByUsername", Parent.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    /**
     *
     * @param b
     * @param p
     */
    public void createBabyForParent(Baby b, Parent p) {
        //supposed to be a new entity
        em.persist(b);

        //Get managed reference of the parent parameter for consistency
        Parent managedParentReference = em.getReference(Parent.class, p.getId());
        managedParentReference.addBaby(b);
        em.merge(managedParentReference);
    }

    /**
     *
     * @param b
     */
    public void updateBabyForParent(Baby b) {
        Baby managedBabyReference = em.getReference(Baby.class, b.getId());
        if (!managedBabyReference.getFirstName().equals(b.getFirstName())) {
            managedBabyReference.setFirstName(b.getFirstName());
        }
        if (!managedBabyReference.getLastName().equals(b.getLastName())) {
            managedBabyReference.setLastName(b.getLastName());
        }
        if (b.getBirthDate() != null) {
            managedBabyReference.setBirthDate(b.getBirthDate());
        }

        managedBabyReference.setGender(b.getGender());
        em.merge(managedBabyReference);
    }

    /**
     *
     * @param b
     */
    public void deleteBabyForParent(Baby b) {
        Baby managedBabyReference = em.getReference(Baby.class, b.getId());

        for (Parent p : new ArrayList<Parent>(managedBabyReference.getParents())) {
            p.removeBaby(managedBabyReference);
            em.merge(p);
        }
        List<DeliveryVisit> delvisit
                = em.createNamedQuery("DeliveryVisit.findVisitByBaby", DeliveryVisit.class)
                        .setParameter("ID", managedBabyReference.getId())
                        .getResultList();
        for (DeliveryVisit d : delvisit) {
            d.deleteVisit();
            em.remove(d);
        }
        em.remove(managedBabyReference);
    }
}
