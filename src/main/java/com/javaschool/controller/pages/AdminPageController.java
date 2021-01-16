package com.javaschool.controller.pages;

import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    private final ContractService contractService;

    @Autowired
    public AdminPageController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping("/lockedContract")
    public String lockedContract(@RequestParam long contractId){
        contractService.lockedContractByAdmin(contractId);
        return "redirect:/contracts";
    }
}
