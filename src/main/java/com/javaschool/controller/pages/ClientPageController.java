package com.javaschool.controller.pages;

import com.javaschool.dto.ClientDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientPageController {

    private final ClientService clientService;

    @Autowired
    public ClientPageController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/userProfile")
    public ModelAndView getPersonalPage(){
        ClientDto client = clientService.getAll().get(2);
        ModelAndView mav = new ModelAndView("jsp/client/userProfile/userProfile");
        mav.addObject("client", client);
        return mav;
    }
}
