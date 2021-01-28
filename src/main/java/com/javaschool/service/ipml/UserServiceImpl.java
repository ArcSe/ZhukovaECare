package com.javaschool.service.ipml;

import com.javaschool.dao.UserDao;
import com.javaschool.dto.UserDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.mapper.UserMapper;
import com.javaschool.model.Role;
import com.javaschool.model.Tariff;
import com.javaschool.model.User;
import com.javaschool.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public List<UserDto> getAll() throws NotDataFoundException {
        if(Objects.isNull(userDao.getAll())){
            throw new NotDataFoundException(User.class.getName());
        }
        return userDao.getAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<User> getAllEntity() throws NotDataFoundException {
        if(Objects.isNull(userDao.getAll())) {
            throw new NotDataFoundException(User.class.getName());
        }
        return userDao.getAll();
    }

    @Transactional
    @Override
    public void add(UserDto user) {
        userDao.add(userMapper.toEntity(user));
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public void update(UserDto user) {
        userDao.update(userMapper.toEntity(user));
    }

    @Override
    public UserDto getById(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(userDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        return userMapper.toDto(userDao.getById(id));
    }

    @Override
    public User getByUserEmail(String email)  {
        return userDao.getByEmail(email);
    }

    @Override
    public boolean save(UserDto user)  {
        User userFromDb = getByUserEmail(user.getEmail());

        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(userMapper.toEntity(user));

        return true;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return getByUserEmail(s);
    }
}
