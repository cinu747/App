/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.service;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Hospital;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cini
 */
@Named
@Stateless
public class HospitalService {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    /**
     * Hospital service constructor
     */
    public HospitalService() {

    }

    /**
     * Create hospital , use persistance
     * @param h
     */
    public void create(Hospital h) {
        em.persist(h);
    }

    /**
     *
     * @param id
     * @return
     */
    public Hospital read(Long id) {
        return em.find(Hospital.class, id);
    }

    /**
     * Update the hospital details
     * @param h
     */
    public void update(Hospital h) {
        em.merge(h);

    }

    /**
     * delete/cancellation
     * @param h
     */
    public void delete(Hospital h) {
        em.remove(em.merge(h));

    }

    /**
     * List to findall 
     * @return
     */
    public List<Hospital> findAll() {
        List<Hospital> hospitals = new ArrayList<>();
        hospitals = em.createNamedQuery("Hospital.findAll", Hospital.class).getResultList();
        return hospitals;
    }

}
