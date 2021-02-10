package com.javaschool.controller.pages.client;

import com.javaschool.dto.ContractDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.service.ContractService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ContractClientController {

    final ContractService contractService;
    private final TariffService tariffService;

    @Autowired
    public ContractClientController(ContractService contractService, TariffService tariffService) {
        this.contractService = contractService;
        this.tariffService = tariffService;
    }



}
