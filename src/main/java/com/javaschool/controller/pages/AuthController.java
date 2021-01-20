package com.javaschool.controller.pages;

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

    @Autowired
    private UserService userService;
    private UserMapper mapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/auth/registration")
    public String registration(Map<String, Object> model){
        User user = new User();
        model.put("user", user);
        return "jsp/auth/registration";
    }

    @PostMapping("/auth/registration")
    public String addUser(@ModelAttribute("user") User user, Map<String, Object> model) {
 /*
        User userFromDb = userService.getByUserEmail(user.getEmail());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "redirect:/login";
        }
*/
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);

        return "redirect:/login";
    }
}
