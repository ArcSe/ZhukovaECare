package com.javaschool.service;

import com.javaschool.dto.UserDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<UserDto> getAll() throws NotDataFoundException;

    List<User> getAllEntity() throws NotDataFoundException;

    void add(UserDto user);

    void delete(long id);

    void update(UserDto user);

    UserDto getById(long id) throws ExamplesNotFoundException;

    User getByUserEmail(String email) throws ExamplesNotFoundException;

    @Transactional
    boolean save(UserDto user) ;
}
