package com.javaschool.controller.pages;

import com.javaschool.dto.OptionDto;
import com.javaschool.dto.UserDto;
import com.javaschool.model.User;
import com.javaschool.service.ContractService;
import com.javaschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    private final ContractService contractService;
    @Autowired
    UserService userService;

    @Autowired
    public AdminPageController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping("/lockedContract")
    public String lockedContract(@RequestParam long contractId){
        contractService.lockedContractByAdmin(contractId);
        return "redirect:/contracts";
    }

    @RequestMapping("/users")
    public ModelAndView getAllUsers() {
        System.out.println(userService.getAllEntity());
        List<User> listUser = userService.getAllEntity();
        ModelAndView mav = new ModelAndView("jsp/admin/user/users");
        mav.addObject("listUsers", listUser);
        return mav;
    }
}
