package com.javaschool.controller.pages.manager;

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

import java.util.*;

@Controller
@RequestMapping("/managers/contracts")
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

    @RequestMapping()
    public ModelAndView home(){
        List<ContractDto> contracts = contractService.getAll();
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/contractList");
        mav.addObject("contracts", contracts);
        return mav;
    }

    @RequestMapping("/getById")
    public ModelAndView getById(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/contractInfoPage");
        mav.addObject("contract", contractService.getById(id));
        return mav;
    }

    @RequestMapping("/new")
    public String newContract(Map<String, Object> model) {
        ContractDto contractDto = new ContractDto();
        List<ClientDto> clients = clientService.getAll();
        List<TariffDto> tariffs = tariffService.getAll();
        model.put("tariffs", tariffs);
        model.put("clients", clients);
        model.put("contract", contractDto);
        return "jsp/managers/contracts/new_contract";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveContract(@RequestParam("tariff.id") long tariffsId,
                               @ModelAttribute("contract") ContractDto contract) {
        contract.setTariff(tariffService.getById(tariffsId));
        contractService.add(contract);
        return "redirect:/managers/contracts";
    }

    @RequestMapping(value ="/delete", method = RequestMethod.POST)
    public String deleteContractById(@RequestParam long id) {
        contractService.delete(id);
        return "redirect:/managers/contracts";
    }

    @RequestMapping(value ="/addClient")
    public ModelAndView addClient(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/add_client");
        ContractDto contractDto = contractService.getById(id);
        List<ClientDto> clientDtos = clientService.getAll();
        mav.addObject("clients", clientDtos);
        mav.addObject("contract", contractDto);
        return mav;
    }

    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    public String updateClients(@RequestParam("contract.id") long contractId,
                                @RequestParam("client.id") long clientId) {
        ContractDto contractDto = contractService.getById(contractId);
        contractDto.setClientId(clientId);
        contractService.update(contractDto);
        return "redirect:/managers/contracts";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateTariff(@RequestParam("tariff.id") long tariffsId,
                               @ModelAttribute("contract") ContractDto contract) {

        contract.setTariff(tariffService.getById(tariffsId));
        contractService.update(contract);
        return "redirect:/managers/contracts";
    }

    @RequestMapping("/edit")
    public ModelAndView editTariff(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/edit_contract");
        ContractDto contractDto = contractService.getById(id);
        List<TariffDto> tariffs = tariffService.getAll();
        mav.addObject("tariff", tariffs);
        mav.addObject("contract", contractDto);
        return mav;
    }

    @RequestMapping("/addOption")
    public ModelAndView editOption(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/addOptions");
        mav.addObject("contract", contractService.getById(id));
        mav.addObject("options", optionService.getAll());
        return mav;
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    private String addOption(@RequestParam("optionId") long optionId,
                               @RequestParam("contractId") long contractId){
        contractService.addOption(optionId, contractId);
        //editOption(optionId);
        return "redirect:/managers/contracts/addOption?id="+ contractId;
    }

    @RequestMapping(value ="/deleteOption", method = RequestMethod.POST)
    public String  deleteOption(@RequestParam("optionId") long optionId,
                                @RequestParam("contractId") long contractId){
        contractService.deleteOptions(optionId, contractId);
        return "redirect:/managers/contracts/addOption?id="+ contractId;
    }


}
