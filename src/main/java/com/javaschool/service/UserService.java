package com.javaschool.service;

import com.javaschool.dto.UserDto;
import com.javaschool.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    void add(UserDto option);

    void delete(long id);

    void update(UserDto option);

    UserDto getById(long id);

    User getByUserEmail(String email);

    @Transactional
    void save(User client);
}
