package com.javaschool.dao;

import com.javaschool.model.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OptionDaoImpl implements OptionDao{

    @PersistenceContext
    private EntityManager emf;

    @Override
    public List<Option> getAll() {
        return emf.createQuery("select c from Option c").getResultList();
    }
}
