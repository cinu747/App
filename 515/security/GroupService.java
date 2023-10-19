/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.security;

import edu.iit.sat.itmd4515.ckolathabraham.service.AbstractService;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Cini
 */
@Stateless
public class GroupService extends AbstractService<Group> {

    /**
     *
     */
    public GroupService() {
        super(Group.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Group> findAll() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }

}
