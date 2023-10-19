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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Cini
 */
@Entity
@NamedQuery(name = "Hospital.findAll", query = "select h from Hospital h")
public class Hospital extends AbstractNamedEntity {

    private String HospitalName;
    private String City;
    @OneToMany(mappedBy = "Hospital")
    private List<DeliveryVisit> visits = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     * Get the user  using the getter method
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter method for the user
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * no args constructor for hosptial
     */
    public Hospital() {
    }

    /**
     *
     * @param HospitalName
     * @param City
     */
    public Hospital(String HospitalName, String City) {
        this.HospitalName = HospitalName;
        this.City = City;
    }

    /**
     * Make a list for delivery visit
     * @return
     */
    public List<DeliveryVisit> getVisits() {
        return visits;
    }

    /**
     * Set the visits
     * @param visits
     */
    public void setVisits(List<DeliveryVisit> visits) {
        this.visits = visits;
    }

    /**
     * Get the city name
     * @return
     */
    public String getCity() {
        return City;
    }

    /**
     *
     * @param City
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     *
     * @return
     */
    public String getHospitalName() {
        return HospitalName;
    }

    /**
     *
     * @param HospitalName
     */
    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

//    public Long getId() {
//        return id;
//    }
//
//   
//    public void setId(Long id) {
//        this.id = id;
//    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Hospital{" + "id=" + Id + ", HospitalName=" + HospitalName + ", City=" + City + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Hospital other = (Hospital) obj;
        if ((this.Id == null) || (other.Id == null)) {
            return false;
        }
        return Objects.equals(this.Id, other.Id);
    }

}
