package com.javaschool.service;

import com.javaschool.dto.UserDto;
import com.javaschool.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<UserDto> getAll();

    List<User> getAllEntity();

    void add(UserDto user);

    void delete(long id);

    void update(UserDto user);

    UserDto getById(long id);

    User getByUserEmail(String email);

    @Transactional
    boolean save(UserDto user);
}
