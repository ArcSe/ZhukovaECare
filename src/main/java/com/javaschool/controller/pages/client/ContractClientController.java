package com.javaschool.controller.pages.client;

import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client")
public class ContractClientController {

    private final ContractService contractService;

    @Autowired
    public ContractClientController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping("/lockedContract")
    public String lockedContract(@RequestParam long contractId) throws Exception{
        contractService.lockedContract(contractId);
        return "redirect:/client/userProfile";
    }
}
