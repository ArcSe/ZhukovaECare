package com.javaschool.controller.pages.client;

import com.javaschool.dto.ClientDto;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.model.User;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/client")
public class ClientPageController {

    private final ClientService clientService;
    private final ContractService contractService;
    private final TariffService tariffService;

    @Autowired
    public ClientPageController(ClientService clientService, ContractService contractService,
                                TariffService tariffService) {
        this.clientService = clientService;
        this.contractService = contractService;
        this.tariffService = tariffService;
    }

    @RequestMapping("/userProfile")
    public ModelAndView getPersonalPage(@AuthenticationPrincipal User user) throws Exception{
        ModelAndView mav = new ModelAndView();
        if(Objects.isNull(user.getClient())) {
            mav = new ModelAndView("jsp/client/userProfile/userProfile");
            mav.addObject("email", user.getEmail());
        }
        else {
            mav = new ModelAndView("jsp/client/userProfile/clientProfile");

            long clientId = user.getClient().getId();
            ClientDto client = clientService.getById(clientId);
            mav.addObject("client", client);
        }
        return mav;
    }

    @RequestMapping("/contractInfo")
    public ModelAndView getContractById(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/contractInfoPage");
        mav.addObject("contract", contractService.getById(id));
        return mav;
    }

    @RequestMapping("/addTariff")
    public ModelAndView chooseTariff(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/client/userProfile/addTariff");
        mav.addObject("contract", contractService.getById(id));
        mav.addObject("listTariff", tariffService.getAll());
        return mav;
    }
    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    private String addOption(@RequestParam("tariffId") long tariffId,
                             @RequestParam("contractId") long contractId) throws Exception {
        ContractDto contractDto = contractService.getById(contractId);
        TariffDto tariffDto = tariffService.getById(tariffId);
        contractDto.setTariff(tariffDto);
        contractService.update(contractDto);
        return "redirect:/client/addTariff?id="+ contractId;
    }

}
