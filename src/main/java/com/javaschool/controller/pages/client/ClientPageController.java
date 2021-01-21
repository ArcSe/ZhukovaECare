package com.javaschool.controller.pages.client;

import com.javaschool.dto.ClientDto;
import com.javaschool.dto.UserDto;
import com.javaschool.model.User;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientPageController {

    private final ClientService clientService;
    private final ContractService contractService;

    @Autowired
    public ClientPageController(ClientService clientService, ContractService contractService) {
        this.clientService = clientService;
        this.contractService = contractService;
    }

    @RequestMapping("/userProfile")
    public ModelAndView getPersonalPage(@AuthenticationPrincipal User user){
        ModelAndView mav = new ModelAndView("jsp/client/userProfile/userProfile");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/lockedContract")
    public String lockedContract(@RequestParam long contractId){
        contractService.lockedContract(contractId);
        return "redirect:/client/userProfile";
    }
}
