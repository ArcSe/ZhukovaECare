package com.javaschool.service.ipml;

import com.javaschool.dao.UserDao;
import com.javaschool.dto.UserDto;
import com.javaschool.mapper.UserMapper;
import com.javaschool.model.User;
import com.javaschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserDao userDao;
    private final UserMapper clientMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper clientMapper) {
        this.userDao = userDao;
        this.clientMapper = clientMapper;
    }


    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(UserDto client) {
        userDao.add(clientMapper.toEntity(client));
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public void update(UserDto client) {
        userDao.update(clientMapper.toEntity(client));
    }

    @Override
    public UserDto getById(long id) {
        return clientMapper.toDto(userDao.getById(id));
    }

    @Override
    public User getByUserEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public void save(User client) {
        System.out.println(client.getEmail() + " " + client.getPassword() + " " + client.isActive());
        userDao.save(client);
    }
}
