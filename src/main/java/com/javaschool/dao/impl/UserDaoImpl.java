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
        TypedQuery<User> query = em.createQuery("Select u from User u " + "where u.email LIKE "
                + "?1 ", User.class);
        query.setParameter(1, email);
        User foundUser=null;
        try {
            foundUser = query.getSingleResult();
        }
        catch (NoResultException e){
            //!!!Change to log!!!
            System.out.println(e.getCause());
        }
        return foundUser;
    }
}
