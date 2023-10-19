/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.security;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Cini
 */
@Entity
@Table(name = "sec_user")
@EntityListeners(UserListener.class)
@NamedQuery(name = "User.findAll", query = "select u from User u")
public class User {

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", enabled=" + enabled + ", groups=" + groups + '}';
    }

    @Id
    @NotBlank(message = "Username cannot be blank")
    private String userName;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    private Boolean enabled;
    @ManyToMany
    @JoinTable(name = "sec_user_groups",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))

    private List<Group> groups = new ArrayList<>();

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param UserName
     * @param password
     * @param enabled
     */
    public User(String UserName, String password, Boolean enabled) {
        this.userName = UserName;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     *
     * @param g
     */
    public void addGroup(Group g) {
        //checks to be performed
        this.groups.add(g);
        g.getUsers().add(this);
    }

    /**
     *
     * @param g
     */
    public void removeGroup(Group g) {
        this.groups.remove(g);
        g.getUsers().remove(this);
    }

    /**
     *
     * @return
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     *
     * @param groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     *
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
