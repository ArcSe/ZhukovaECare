package com.javaschool.controller.pages;

import com.javaschool.dto.ClientDto;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView getPersonalPage(){
        ClientDto client = clientService.getAll().get(2);
        ModelAndView mav = new ModelAndView("jsp/client/userProfile/userProfile");
        mav.addObject("client", client);
        return mav;
    }

    @RequestMapping("/lockedContract")
    public String lockedContract(@RequestParam long contractId){
        contractService.lockedContract(contractId);
        return "redirect:/client/userProfile";
    }
}
