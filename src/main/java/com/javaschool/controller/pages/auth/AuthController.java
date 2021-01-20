package com.javaschool.controller.pages.auth;

import com.javaschool.dao.UserDao;
import com.javaschool.dto.UserDto;
import com.javaschool.mapper.UserMapper;
import com.javaschool.model.User;
import com.javaschool.model.Role;
import com.javaschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/registration")
    public String registration(Map<String, Object> model){
        model.put("user", new UserDto());
        return "jsp/auth/registration";
    }

    @PostMapping("/auth/registration")
    public String addUser(@ModelAttribute("user") UserDto user, Map<String, Object> model) {
        userService.save(user);
        return "redirect:/login";
    }
}
