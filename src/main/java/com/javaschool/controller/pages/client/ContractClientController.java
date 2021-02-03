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

    @RequestMapping("/lockedContract")
    public String lockedContract(@RequestParam long contractId) throws Exception{
        contractService.lockedContract(contractId);
        return "redirect:/client/userProfile";
    }

    @RequestMapping("/contract")
    public String addNewContract(@RequestParam long id, Model model) throws Exception{
        ContractDto contractDto = new ContractDto();
        contractDto.setClientId(id);
        contractDto.setNumber(contractService.generatePhoneNumber());
        List<TariffDto> tariffs = tariffService.getAll();
        model.addAttribute("tariffs", tariffs);
        model.addAttribute("contract", contractDto);
        return "jsp/managers/contracts/new_contract";
    }

    @RequestMapping("/save")
    public String saveNewContract(@RequestParam("tariff.id") long tariffsId,
                                  @ModelAttribute("contract") ContractDto contract) throws Exception{
        contract.setTariff(tariffService.getById(tariffsId));
        contractService.add(contract);
        return "redirect:/client/userProfile";
    }

}
