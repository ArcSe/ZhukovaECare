package com.javaschool.controller.pages.admin;

import com.javaschool.dto.ClientDto;
import com.javaschool.dto.UserDto;
import com.javaschool.model.Role;
import com.javaschool.service.ClientService;
import com.javaschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    final UserService userService;
    final ClientService clientService;

    @Autowired
    public AdminUserController(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @RequestMapping()
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("jsp/admin/user/users");
        mav.addObject("listUsers", userService.getAll());
        return mav;
    }

    @RequestMapping(value ="/addClientId")
    public ModelAndView addClient(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/admin/user/addClient");
        UserDto userDto = userService.getById(id);
        List<ClientDto> clientDtos = clientService.getAll();
        mav.addObject("clients", clientDtos);
        mav.addObject("user", userDto);
        return mav;
    }

    @RequestMapping(value = "/addClientId", method = RequestMethod.POST)
    public String updateClients(@RequestParam("user.id") long userId,
                                @RequestParam("client.id") long clientId) {
        UserDto userDto = userService.getById(userId);
        userDto.setClientId(clientId);
        userService.update(userDto);
        return "redirect:/admin/users";
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

    @PostMapping("/delete")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.delete(userId);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.getById(userId));
        return "redirect:/admin/users";
    }
}
