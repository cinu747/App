/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.demo;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import edu.iit.sat.itmd4515.ckolathabraham.domain.BabyGender;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Cini
 */
public class Demo {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("New baby to the world");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Baby baby = new Baby();
        //b.setId(999l);
        baby.setFirstName("Prescilla");
        baby.setGender(BabyGender.FEMALE);
        System.out.println(baby.toString());
        em.persist(baby);
        tx.commit();
    }
}
