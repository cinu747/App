/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.domain;

import edu.iit.sat.itmd4515.ckolathabraham.security.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Cini
 */
@Entity
@NamedQuery(name = "Parent.findAll", query = "select p from Parent p")
@NamedQuery(name = "Parent.findByUsername", query = "select p from Parent p where p.user.userName = :username")
public class Parent extends AbstractNamedEntity {

    private String fatherName;
    private String motherName;
    private int patient_id;
    private int contact;
    private String email;

    @ManyToMany
    @JoinTable(name = "PARENT_BABIES",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "BABY_ID"))
    List<Baby> babies = new ArrayList<>();
    //Inverse or non-owning side of the bi-directional relationship between visit and the parent
    @OneToMany(mappedBy = "parent")
    private List<DeliveryVisit> visits = new ArrayList<>();
//(mappedBy = "patient_id")
    @OneToOne
    private List<Hospital> hospital = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Parent() {
    }

    /**
     *
     * @param fatherName
     * @param motherName
     * @param patient_id
     * @param contact
     * @param email
     */
    public Parent(String fatherName, String motherName, int patient_id, int contact, String email) {
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.patient_id = patient_id;
        this.contact = contact;
        this.email = email;
    }

    /**
     *
     * @param b
     */
    public void addBaby(Baby b) {

        if (!this.babies.contains(b)) {
            this.babies.add(b);
        }
        if (!b.getParents().contains(this)) {
            b.getParents().add(this);
        }

    }

    /**
     *
     * @param b
     */
    public void removeBaby(Baby b) {
        if (this.babies.contains(b)) {
            this.babies.remove(b);
        }
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public int getPatient_id() {
        return patient_id;
    }

    /**
     *
     * @param patient_id
     */
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    /**
     *
     * @return
     */
    public int getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(int contact) {
        this.contact = contact;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
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
     * @return
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     *
     * @param fatherName
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Parent{" + "id=" + Id + ", fatherName=" + fatherName
                + "user=" + user + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.Id);
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
        final Parent other = (Parent) obj;
        if ((this.Id == null) || (other.Id == null)) {
            return false;
        }
        return Objects.equals(this.Id, other.Id);
    }

    /**
     *
     * @return
     */
    public List<Baby> getBabies() {
        return babies;
    }

    /**
     *
     * @param babies
     */
    public void setBabies(List<Baby> babies) {
        this.babies = babies;
    }

    /**
     *
     * @return
     */
    public List<DeliveryVisit> getVisits() {
        return visits;
    }

    /**
     *
     * @param visits
     */
    public void setVisits(List<DeliveryVisit> visits) {
        this.visits = visits;
    }
}
