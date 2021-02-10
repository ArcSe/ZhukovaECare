package com.javaschool.dao;

import com.javaschool.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends AbstractDao<User>{
    User getByEmail(String email);

    void save(User user);
}
