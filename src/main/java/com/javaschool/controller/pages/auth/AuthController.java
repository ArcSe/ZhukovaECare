package com.javaschool.controller.pages.auth;

import com.javaschool.controller.pages.ControllerUtils;
import com.javaschool.dao.UserDao;
import com.javaschool.dto.UserDto;
import com.javaschool.mapper.UserMapper;
import com.javaschool.model.User;
import com.javaschool.model.Role;
import com.javaschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    public String addUser( @Valid @ModelAttribute("user") UserDto user,
                          BindingResult bindingResult,
                          Model model) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            model.addAttribute("user", userDto);

            return "jsp/auth/registration";
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordConfirmError", "Password don't match!");
            return "jsp/auth/registration";
        }

        if (!userService.save(user)) {
            model.addAttribute("emailError", "User is already exist!");
            return "jsp/auth/registration";
        }


        userService.save(user);
        return "redirect:/login";
    }
}
