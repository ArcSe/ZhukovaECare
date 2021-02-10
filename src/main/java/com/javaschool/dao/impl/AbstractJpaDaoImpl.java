package com.javaschool.dao.impl;

import com.javaschool.dao.AbstractDao;
import com.javaschool.model.AbstractModel;
import com.javaschool.model.Option;
import com.javaschool.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class AbstractJpaDaoImpl<T extends AbstractModel> implements AbstractDao<T> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public AbstractJpaDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }
    @Transactional
    public List<T> getAll(){
        return em.createQuery("select c from "+  entityClass.getName() + " c").getResultList();
    }


    @Transactional
    public void add(T o){
        em.persist(o);
    }

    @Transactional
    public T getById(long id){
        TypedQuery<T> query = em.createQuery("Select c from " + entityClass.getName() + " c " + "where c.id LIKE "
                + "?1 ", entityClass);
        query.setParameter(1, id);
        T foundUser=null;
        try {
            foundUser = query.getSingleResult();
        }
        catch (NoResultException e){
            //!!!Change to log!!!
            System.out.println(e.getCause());
        }
        return foundUser;
    }

    @Transactional
    public void delete(long id){
        em.remove(getById(id));
    }

    @Transactional
    public void update(T o){
        em.merge(o);
    }
}
