package com.javaschool.controller.pages.admin;

import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminContractsController {

    final ContractService contractService;

    @Autowired
    public AdminContractsController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping("/lockedContract")
    public String lockedContract(@RequestParam long contractId) throws Exception{
        contractService.lockedContractByAdmin(contractId);
        return "redirect:/managers/contracts";
    }
}
