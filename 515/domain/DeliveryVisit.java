/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author Cini
 */
@Entity
@NamedQuery(name = "DeliveryVisit.findAll", query = "select d from DeliveryVisit d")
@NamedQuery(name = "DeliveryVisit.findVisitByBaby", query = "select d from DeliveryVisit d where d.baby.Id = :ID")
public class DeliveryVisit extends AbstractNamedEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private LocalDate dateofvisit;

    private LocalTime timeofvisit;

    /**
     * Get the value of timeofvisit
     *
     * @return the value of timeofvisit
     */
    public LocalTime getTimeofvisit() {
        return timeofvisit;
    }

    /**
     * Set the value of timeofvisit
     *
     * @param timeofvisit new value of timeofvisit
     */
    public void setTimeofvisit(LocalTime timeofvisit) {
        this.timeofvisit = timeofvisit;
    }

    private String motherName;
    private String doctorName;
    private String visitType;

    //bi-directional between parent and the visit
    //visit is the owning side
    @ManyToOne
    private Parent parent;

    //uni-directional between visit and the baby
    @ManyToOne
    private Baby baby;

    //bi-directional between visit and the hospital
    @ManyToOne
    private Hospital hospital;

    /**
     *
     * @return
     */
    public Hospital getHospital() {
        return hospital;
    }

    /**
     *
     * @param hospital
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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
    public Parent getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     *
     */
    public DeliveryVisit() {
    }

    /**
     *
     * @param dateofvisit
     * @param visitTime
     * @param motherName
     * @param doctorName
     */
    public DeliveryVisit(LocalDate dateofvisit, LocalTime visitTime, String motherName, String doctorName) {
        this.dateofvisit = dateofvisit;
        this.timeofvisit = visitTime;
        this.motherName = motherName;
        this.doctorName = doctorName;
    }

    /**
     *
     * @param p
     * @param b
     * @param h
     */
    public void scheduleVisit(Parent p, Baby b, Hospital h) {
        this.parent = p;
        this.hospital = h;
        this.baby = b;

        if (!p.getVisits().contains(this)) {
            p.getVisits().add(this);
        }
        if (!h.getVisits().contains(this)) {
            h.getVisits().add(this);
        }

    }

    /**
     *
     */
    public void deleteVisit() {

        if (this.parent.getVisits().contains(this)) {
            this.parent.getVisits().remove(this);
        }

        if (this.hospital.getVisits().contains(this)) {
            this.hospital.getVisits().remove(this);
        }
        this.parent = null;
        this.hospital = null;
        this.baby = null;

    }

    /**
     *
     * @return
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     *
     * @param doctorName
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     *
     * @return
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     *
     * @param motherName
     */
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    /**
     *
     * @return //
     */
//    public LocalTime getvisitTime() {
//        return timeofvisit;
//    }
//
//    /**
//     *
//     * @param visitTime
//     */
//    public void setvisitTime(LocalTime visitTime) {
//        this.timeofvisit = visitTime;
//    }
    /**
     *
     * @return
     */
    public String getVisitType() {
        return visitType;
    }

    /**
     *
     * @param visitType
     */
    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateofvisit() {
        return dateofvisit;
    }

    /**
     *
     * @param dateofvisit
     */
    public void setDateofvisit(LocalDate dateofvisit) {
        this.dateofvisit = dateofvisit;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "DeliveryVisit{" + "id=" + Id + ", dateofvisit=" + dateofvisit + ",visitTime=" + timeofvisit + ", motherName=" + motherName + ", doctorName=" + doctorName + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.Id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DeliveryVisit other = (DeliveryVisit) obj;
        if ((this.Id == null) || (other.Id == null)) {
            return false;
        }
        return Objects.equals(this.Id, other.Id);
    }
}
