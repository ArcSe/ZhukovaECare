package com.javaschool.dao.impl;

import com.javaschool.dao.OptionDao;
import com.javaschool.model.Option;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionDaoImpl extends AbstractJpaDaoImpl<Option> implements OptionDao {


    public List<Option> getAllNotDeleted(){
        return em.createQuery("select c from   Option  c where c.deleted=false ").getResultList();
    }
}

