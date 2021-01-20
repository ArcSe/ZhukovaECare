package com.javaschool.dao.impl;

import com.javaschool.dao.ClientDao;
import com.javaschool.dao.UserDao;
import com.javaschool.model.Client;
import com.javaschool.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractJpaDaoImpl<User> implements UserDao {

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User getByEmail(String email) {
        return em.find(User.class, email);
    }
}
