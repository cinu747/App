/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.security;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Parent;
import edu.iit.sat.itmd4515.ckolathabraham.service.AbstractService;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Cini
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     *
     */
    public UserService() {
        super(User.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    /**
     *
     * @param p
     */
    public void signupNewParentUser(Parent p) {
        //create the non-owning entity
        Group parentGroup
                = em.createQuery("select g from Group g where g.groupName = 'PARENT_GROUP'", Group.class).getSingleResult();

        p.getUser().addGroup(parentGroup);
        p.getUser().setEnabled(true);
        em.persist(p.getUser());
        //persist the owning entity
        em.persist(p);

    }
}
