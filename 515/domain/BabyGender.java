/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.domain;

/**
 *
 * @author Cini
 */
public enum BabyGender {

    /**
     *
     */
    FEMALE("Baby is a girl"),
    /**
     *
     */
    MALE("Baby is a boy");

    private String gender;

    private BabyGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

}
