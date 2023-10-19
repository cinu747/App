/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.service;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Cini
 */
@Named
@Stateless
public class BabyService extends AbstractService<Baby> {

    /**
     *
     */
    public BabyService() {
        super(Baby.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Baby> findAll() {
        return em.createNamedQuery("Baby.findAll", Baby.class).getResultList();

    }
}
