package com.javaschool.controller.crud;

import com.javaschool.dto.ClientDto;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class ClientController {

    private final ClientService clientService;
    private final ContractService contractService;

    @Autowired
    public ClientController(ClientService clientService, ContractService contractService) {
        this.clientService = clientService;
        this.contractService = contractService;
    }

    @RequestMapping("/clients")
    public ModelAndView home() {
        List<ClientDto> listClient = clientService.getAll();
        ModelAndView mav = new ModelAndView("clients/home");
        mav.addObject("listClient", listClient);
        return mav;
    }

    @RequestMapping("clients/new")
    public String newClient(Map<String, Object> model) {
        ClientDto clientDto = new ClientDto();
        model.put("client", clientDto);
        return "clients/new_client";
    }

    @RequestMapping(value = "clients/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("client") ClientDto client) {
        clientService.add(client);
        return "redirect:/clients";
    }
}
