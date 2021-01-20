package com.javaschool.controller.pages.admin;

import com.javaschool.dto.UserDto;
import com.javaschool.model.Role;
import com.javaschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping()
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("jsp/admin/user/users");
        mav.addObject("listUsers", userService.getAll());
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView editUser(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/admin/user/edit_users");
        mav.addObject("user", userService.getById(id));
        mav.addObject("roles", Role.values());
        return mav;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateOption(@ModelAttribute("user") UserDto user) {
        userService.update(user);
        return "redirect:/";
    }
}
