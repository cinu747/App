/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.service;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Hospital;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cini
 * @param <T>
 */
public abstract class AbstractService<T> {

    //private static final Logger LOG = Logger.getLogger(AbstractService.class.getName());
    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    /**
     *
     */
    protected final Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;

    }

    /**
     *
     * @param entity
     */
    public void create(T entity) {
        //System.out.print("Inside Createee");
        //LOG.info("Inside createeeeee");
        em.persist(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    public T read(Long id) {
        return em.find(entityClass, id);
    }

    /**
     *
     * @param entity
     */
    public void update(T entity) {
        em.merge(entity);

    }

    /**
     *
     * @param entity
     */
    public void delete(T entity) {
        em.remove(em.merge(entity));

    }

    /**
     *
     * @return
     */
    abstract public List<T> findAll();
}
