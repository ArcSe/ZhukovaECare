package com.javaschool.dao.impl;

import com.javaschool.dao.AbstractDao;
import com.javaschool.model.AbstractModel;
import com.javaschool.model.Option;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

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
    public List<T> getAll(){
        return em.createQuery("select c from "+  entityClass.getName() + " c").getResultList();
    }

    @Transactional
    public void add(T o){
        em.persist(o);
    }

    public T getById(long id){
        return em.find(entityClass, id);
    }

    @Transactional
    public void delete(long id){
        em.remove(getById(id));
    }

    @Transactional
    public void update(T o){
        em.merge(o);}
}
