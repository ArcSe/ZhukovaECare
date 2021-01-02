package com.javaschool.dao;

import com.javaschool.model.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OptionDaoImpl implements OptionDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Option> getAll() {
        return em.createQuery("select c from Option c").getResultList();
    }

    @Transactional
    @Override
    public void add(Option option) {
        em.persist(option);
    }

    @Override
    public Option getById(long id) {
        return (Option) em.createQuery("select c from Option c where c.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public void delete(long id) {
        em.remove(getById(id));
    }

    @Transactional
    @Override
    public void update(Option option) {
        em.merge(option);
    }
}
