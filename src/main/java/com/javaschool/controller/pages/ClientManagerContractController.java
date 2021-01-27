package com.javaschool.controller.pages;

import com.javaschool.service.ContractService;
import com.javaschool.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contracts")
public class ClientManagerContractController {

    private final ContractService contractService;
    private final OptionService optionService;

    @Autowired
    public ClientManagerContractController(ContractService contractService, OptionService optionService) {
        this.contractService = contractService;
        this.optionService = optionService;
    }

    @RequestMapping("/addOption")
    public ModelAndView editOption(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/addOptions");
        mav.addObject("contract", contractService.getById(id));
        mav.addObject("options", optionService.getAll());
        return mav;
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    private String addOption(@RequestParam("optionId") long optionId,
                             @RequestParam("contractId") long contractId) throws Exception{
        contractService.addOption(optionId, contractId);
        //editOption(optionId);
        return "redirect:/contracts/addOption?id="+ contractId;
    }

    @RequestMapping(value ="/deleteOption", method = RequestMethod.POST)
    public String  deleteOption(@RequestParam("optionId") long optionId,
                                @RequestParam("contractId") long contractId) throws Exception{
        try {
            contractService.deleteOptions(optionId, contractId);
        }
        catch (Exception e){
            //todo log
        }
        return "redirect:/contracts/addOption?id="+ contractId;
    }

}
