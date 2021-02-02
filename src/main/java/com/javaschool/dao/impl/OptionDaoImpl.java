package com.javaschool.dao.impl;

import com.javaschool.dao.OptionDao;
import com.javaschool.model.Client;
import com.javaschool.model.Option;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OptionDaoImpl extends AbstractJpaDaoImpl<Option> implements OptionDao {


    public List<Option> getAllNotDeleted(){
        return em.createQuery("select c from   Option  c where c.deleted=false ").getResultList();
    }

    @Override
    public Option getByName(String name) {
        TypedQuery<Option> query = em.createQuery("Select u from Option u where  u.name=?1", Option.class);
        query.setParameter(1, name);
        Option foundOption=null;
        try {
            foundOption = query.getSingleResult();
        }
        catch (NoResultException e){
            //todo!!!Change to log!!!
            System.out.println(e.getCause());
        }
        return foundOption;
    }
}

