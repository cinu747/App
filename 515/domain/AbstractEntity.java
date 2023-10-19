/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.domain;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

/**
 *
 * @author cini
 */
@MappedSuperclass
public class AbstractEntity {

    /**
     * Generating Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long Id;

    @Version
    private Long version;

    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;

    /**
     *Create an abstract entity
     */
    public AbstractEntity() {
    }

    // lifecycle methods
    /**
     * This is a PrePersist JPA lifecycle method which sets the createdTimestamp
     * to the current instant
     */
    @PrePersist
    public void createdTimestamp() {
        createdTimestamp = LocalDateTime.now();
    }

    /**
     * Pre updating the timestamp
     */
    @PreUpdate
    public void updatedTimestamp() {
        updatedTimestamp = LocalDateTime.now();
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return Id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.Id = id;
    }

    /**
     *
     * @return
     */
    public Long getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Local time constraint
     * @return
     */
    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     *
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    /**
     *
     * @param updatedTimestamp
     */
    public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

}
