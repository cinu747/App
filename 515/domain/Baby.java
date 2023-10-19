/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

/**
 *
 * @author Cini
 */
@Entity
@NamedQuery(name = "Baby.findAll", query = "select b from Baby b")
@NamedQuery(name = "Baby.findByName", query = "select b from Baby b where b.FirstName = :NAME")
public class Baby extends AbstractEntity {

    @NotBlank
    @Size(min = 3, max = 255)
    private String FirstName;

    @NotBlank
    @Size(min = 3, max = 255)
    private String LastName;

    @PastOrPresent
    private LocalDate BirthDate;
//    //@PastOrPresent
//    private LocalTime BirthTime;
    @Enumerated(EnumType.STRING)
    private BabyGender gender;
    //inverse side of the manytomany bi-directional relationship between parents and babies
    @ManyToMany(mappedBy = "babies")
    private List<Parent> parents = new ArrayList<>();

    /**
     * Get the list of parents
     * @return
     */
    public List<Parent> getParents() {
        return parents;
    }

    /**
     * Initiate the setter method for the list of parents
     * @param parents
     */
    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    /**
     * create a non args constructor
     */
    public Baby() {
    }

    /**
     *
     * @param FirstName
     * @param LastName
     * @param BirthDate
     * @param gender
     */
    public Baby(String FirstName, String LastName, LocalDate BirthDate, BabyGender gender) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthDate = BirthDate;
//        this.BirthTime = BirthTime;
        this.gender = gender;
    }

    /**
     * Property gender of the baby
     * @return
     */
    public BabyGender getGender() {
        return gender;
    }

    /**
     * Setter method
     * @param gender
     */
    public void setGender(BabyGender gender) {
        this.gender = gender;
    }


    /**
     * Get the local date 
     * @return
     */
    public LocalDate getBirthDate() {
        return BirthDate;
    }

    /**
     *
     * @param BirthDate
     */
    public void setBirthDate(LocalDate BirthDate) {
        this.BirthDate = BirthDate;
    }

    /**
     * Last name
     * @return
     */
    public String getLastName() {
        return LastName;
    }

    /**
     *
     * @param LastName
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * First name
     * @return
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     *
     * @param FirstName
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * Return the string
     * @return
     */
    @Override
    public String toString() {
        return "Baby{" + "Id=" + Id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", BirthDate=" + BirthDate + ", gender=" + gender + '}';
    }

    /**
     * Generate the hashcode with override
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.Id);
        return hash;
    }

    /**
     *
     * @param that
     * @return
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        final Baby other = (Baby) that;
        if ((this.Id == null) || (other.Id == null)) {
            return false;
        }
        return Objects.equals(this.Id, other.Id);
    }
}
