package com.javaschool.controller.pages;

import com.javaschool.controller.pages.client.ClientPageController;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.ShoppingCartDto;
import com.javaschool.model.User;
import com.javaschool.service.ContractService;
import com.javaschool.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

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
    public ModelAndView editOption(@AuthenticationPrincipal User user, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                   @RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/addOptions");
        ClientPageController.createShoppingCartDtoSession(mav, shoppingCart);
        ContractDto contractDto = contractService.getById(id);
        mav.addObject("contract", contractDto);
        if (!contractDto.getOptions().isEmpty()) {
            Set<OptionDto> tariffOption = contractDto.getOptions();
            Set<OptionDto> checkedOptions = new HashSet<>();
            tariffOption.forEach(o -> checkedOptions.addAll(optionService.splitSetMandatoryOptions(o.getId())));
            checkedOptions.addAll(tariffOption);
            mav.addObject("options", checkedOptions);
            mav.addObject("clientId", contractDto.getClientId());
            mav.addObject("userId", user.getClient().getId());
        }
        else {
            mav.addObject("options", optionService.getAll());
            mav.addObject("clientId", contractDto.getClientId());
            mav.addObject("userId", user.getClient().getId());
        }
        return mav;
    }


    @RequestMapping(value = "/addOptionToDb", method = RequestMethod.POST)
    private String addOptionToContractToDB(@RequestParam("optionId") long optionId,
                             @RequestParam("contractId") long contractId) throws Exception{
        contractService.addOption(optionId, contractId);
        //editOption(optionId);
        return "redirect:/contracts/addOption?id="+ contractId;
    }

    @RequestMapping(value ="/deleteOption", method = RequestMethod.POST)
    public String  deleteOption(@RequestParam("optionId") long optionId,
                                @RequestParam("contractId") long contractId) throws Exception{
        contractService.deleteOptions(optionId, contractId);
        return "redirect:/contracts/addOption?id="+ contractId;
    }

}
