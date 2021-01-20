package com.javaschool.dao.impl;

import com.javaschool.dao.ClientDao;
import com.javaschool.dao.UserDao;
import com.javaschool.model.Client;
import com.javaschool.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl extends AbstractJpaDaoImpl<User> implements UserDao {

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User getByEmail(String email) {
        return (User)em.createQuery("select c from User c where c.email = '" + email +"'").getSingleResult();
    }
}
