package com.javaschool.controller.crud;

import com.javaschool.dto.ClientDto;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import com.javaschool.service.OptionService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ContractController {

    private final ContractService contractService;
    private final TariffService tariffService;
    private final OptionService optionService;
    private final ClientService clientService;

    @Autowired
    public ContractController(ContractService contractService, TariffService tariffService, OptionService optionService, ClientService clientService) {
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.clientService = clientService;
    }

    @RequestMapping("contracts")
    public ModelAndView home(){
        List<ContractDto> contracts = contractService.getAll();
        ModelAndView mav = new ModelAndView("jsp/contracts/home");
        mav.addObject("contracts", contracts);
        return mav;
    }


    @RequestMapping(value = "contracts/update", method = RequestMethod.POST)
    public String updateOption(@RequestParam("option.id") long optionId,
                               @RequestParam("tariff.id") long tariffsId,
                               @ModelAttribute("contract") ContractDto contract) {
        OptionDto optionDto = optionService.getById(optionId);
        if(null==contract.getOptions()){
            Set<OptionDto> set = new HashSet<>();
            set.add(optionDto);
            contract.setOptions(set);
        }
        else {
            contract.getOptions().add(optionDto);
        }
        contract.setTariff(tariffService.getById(tariffsId));
        contractService.update(contract);
        return "redirect:/contracts";
    }



    @RequestMapping("contracts/edit")
    public ModelAndView editOption(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/contracts/edit_contract");
        ContractDto contractDto = contractService.getById(id);
        List<OptionDto> options = optionService.getAll();
        List<TariffDto> tariffs = tariffService.getAll();
        mav.addObject("tariff", tariffs);
        mav.addObject("options", options);
        mav.addObject("contract", contractDto);
        return mav;
    }

    @RequestMapping("contracts/new")
    public String newContract(Map<String, Object> model) {
        ContractDto contractDto = new ContractDto();
        List<OptionDto> options = optionService.getAll();
        List<TariffDto> tariffs = tariffService.getAll();
        model.put("tariffs", tariffs);
        model.put("options", options);
        model.put("contract", contractDto);
        return "jsp/contracts/new_contract";
    }

    @RequestMapping(value = "contracts/save", method = RequestMethod.POST)
    public String saveContract(@RequestParam("option.id") long optionId,
                               @RequestParam("tariff.id") long tariffsId,
                               @ModelAttribute("contract") ContractDto contract) {
        OptionDto optionDto = optionService.getById(optionId);
        if(null==contract.getOptions()){
            Set<OptionDto> set = new HashSet<>();
            set.add(optionDto);
            contract.setOptions(set);
        }
        else {
            contract.getOptions().add(optionDto);
        }
        contract.setTariff(tariffService.getById(tariffsId));
        contractService.add(contract);
        return "redirect:/contracts";
    }

    @RequestMapping(value ="contracts/delete", method = RequestMethod.POST)
    public String deleteContractById(@RequestParam long id) {
        contractService.delete(id);
        return "redirect:/contracts";
    }

    @RequestMapping(value ="contracts/addClient")
    public ModelAndView addClient(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/contracts/add_client");
        ContractDto contractDto = contractService.getById(id);
        List<ClientDto> clientDtos = clientService.getAll();
        mav.addObject("clients", clientDtos);
        mav.addObject("contract", contractDto);
        return mav;
    }

    @RequestMapping(value = "contracts/updateClient", method = RequestMethod.POST)
    public String updateClients(@RequestParam("contract.id") long contractId,
                                @RequestParam("client.id") long clientId) {
        ContractDto contractDto = contractService.getById(contractId);
        contractDto.setClientId(clientId);
        contractService.update(contractDto);
        return "redirect:/contracts";
    }

}
