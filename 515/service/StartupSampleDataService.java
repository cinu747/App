/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.service;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Parent;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Baby;
import edu.iit.sat.itmd4515.ckolathabraham.domain.DeliveryVisit;
import edu.iit.sat.itmd4515.ckolathabraham.domain.BabyGender;
import edu.iit.sat.itmd4515.ckolathabraham.domain.Hospital;
import edu.iit.sat.itmd4515.ckolathabraham.security.Group;
import edu.iit.sat.itmd4515.ckolathabraham.security.GroupService;
import edu.iit.sat.itmd4515.ckolathabraham.security.User;
import edu.iit.sat.itmd4515.ckolathabraham.security.UserService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Cini
 */
@Startup
@Singleton
public class StartupSampleDataService {

    private static final Logger LOG = Logger.getLogger(StartupSampleDataService.class.getName());

//    @PersistenceContext(name = "itmd4515PU")
//    private EntityManager em;
    @EJB
    private HospitalService hospSvc;
    @EJB
    private BabyService babySvc;
    @EJB
    private ParentService parentSvc;
    @EJB
    private VisitService visitSvc;
    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;

    /**
     *
     */
    public StartupSampleDataService() {
    }

    @PostConstruct
    private void postConstruct() {
        //Creating the security realm
        Group hospitalGroup = new Group("HOSPITAL_GROUP", "This group represents hospitals at the identity store");
        Group parentGroup = new Group("PARENT_GROUP", "This group represents parents in the identity store");
        Group adminGroup = new Group("ADMIN_GROUP", "This group represents the system administrators in the identity store");
        groupSvc.create(hospitalGroup);
        groupSvc.create(parentGroup);
        groupSvc.create(adminGroup);
        User admin = new User("admin", "admin", true);
        admin.addGroup(adminGroup);
        userSvc.create(admin);
        User hospital1 = new User("hospital1", "hospital1", true);
        hospital1.addGroup(hospitalGroup);
        hospital1.addGroup(adminGroup);
        userSvc.create(hospital1);
        User hospital2 = new User("hospital2", "hospital2", true);
        hospital2.addGroup(hospitalGroup);
        hospital2.addGroup(parentGroup);
        userSvc.create(hospital2);
        User parent1 = new User("parent1", "parent1", true);
        parent1.addGroup(parentGroup);
        userSvc.create(parent1);
        User parent2 = new User("parent2", "parent2", true);
        parent2.addGroup(parentGroup);
        userSvc.create(parent2);

        LOG.info("Inside StartupSampleDataService.postConstruct method");

        Hospital h1 = new Hospital("Rush", "Chicago");
        h1.setUser(hospital1);
        Hospital h2 = new Hospital("AmericanClinic", "Georgia");
        h2.setUser(hospital2);
        Hospital h3 = new Hospital("FederalHospital", "Alaska");
        h3.setUser(hospital2);
//        em.persist(h1);
//        em.persist(h2);
//        em.persist(h3);
        hospSvc.create(h1);
        hospSvc.create(h2);
        hospSvc.create(h3);
        Baby b1 = new Baby("Calista", "Mary", LocalDate.of(2011, 01, 22), BabyGender.FEMALE);
        Baby b2 = new Baby("Nancy", "Ruth", LocalDate.of(2021, 11, 12), BabyGender.FEMALE);
        Baby b3 = new Baby("Joel", "Tom", LocalDate.of(2021, 05, 02), BabyGender.MALE);
        Baby b4 = new Baby("John", "Clem", LocalDate.of(2020, 05, 01), BabyGender.MALE);
        Baby b5 = new Baby("Elijah", "Blessing", LocalDate.of(2022, 07, 24), BabyGender.MALE);
        babySvc.create(b1);
        babySvc.create(b2);
        babySvc.create(b3);
        babySvc.create(b4);
        babySvc.create(b5);

        Parent p1 = new Parent("James", "Carol", 127792, 847 - 475 - 2009, "james@gmail.com");
        p1.setUser(parent1);
        Parent p2 = new Parent("Terry", "Angel", 127891, 312 - 444 - 2009, "angel@gmail.com");
        p2.setUser(parent2);
        Parent p3 = new Parent("Hospital Two ", "as the Parent", 127893, 505 - 441 - 1815, "conel@gmail.com");
        p3.setUser(hospital2);
        p1.addBaby(b1);
        p1.addBaby(b2);
        p2.addBaby(b3);
        p1.addBaby(b4);
        p2.addBaby(b5);
        parentSvc.create(p1);
        parentSvc.create(p2);
        parentSvc.create(p3);

        DeliveryVisit d1 = new DeliveryVisit(LocalDate.of(2021, 12, 12), LocalTime.of(06, 10, 3), "Nancy", "Mercy");
        d1.scheduleVisit(p1, b1, h1);
        DeliveryVisit d2 = new DeliveryVisit(LocalDate.of(2018, 11, 05), LocalTime.of(04, 10, 8), "Calista", "Jacob");
        d2.scheduleVisit(p2, b3, h2);

        visitSvc.create(d1);
        visitSvc.create(d2);

        for (Hospital h : hospSvc.findAll()) {
            LOG.info("Hospital &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& >" + h.toString());
        }
        for (Parent p : parentSvc.findAll()) {
            LOG.info("Parent &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& > " + p.toString());

            for (Baby b : p.getBabies()) {
                LOG.info("\t &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& >" + b.toString());
            }

            for (DeliveryVisit d : p.getVisits()) {
                LOG.info("\t &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& > " + d.toString());
                LOG.info("\t\t &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& > " + d.getParent().toString());
                LOG.info("\t\t &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& > " + d.getBaby().toString());
                LOG.info("\t\t &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& > " + d.getHospital().toString());

            }

        }

    }
}
