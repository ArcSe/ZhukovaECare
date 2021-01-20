package com.javaschool.mapper;

import com.javaschool.dto.UserDto;
import com.javaschool.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {
    @Autowired
    UserMapper() {
        super(User.class, UserDto.class);
    }
}
