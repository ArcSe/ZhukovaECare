package com.javaschool.controller.pages.manager;

import com.javaschool.dto.ClientDto;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/managers/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping()
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("jsp/clients/clientsList");
        mav.addObject("listClient", clientService.getAll());
        return mav;
    }

    @RequestMapping("/getById")
    public ModelAndView getById(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/clients/clientInfoPage");
        mav.addObject("client", clientService.getById(id));
        return mav;
    }

    @RequestMapping("new")
    public String newClient(Map<String, Object> model) {
        model.put("client", new ClientDto());
        return "jsp/clients/new_client";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("client") ClientDto client) {
        clientService.add(client);
        return "redirect:/managers/client";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateClient(@ModelAttribute("client") ClientDto clientDto) {
        clientService.update(clientDto);
        return "redirect:/managers/client";
    }

    @RequestMapping("edit")
    public ModelAndView editClient(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/clients/edit_client");
        ClientDto client = clientService.getById(id);
        mav.addObject("client", client);
        return mav;
    }

    @RequestMapping(value = "delete")
    public String deleteClientById(@RequestParam long id) {
        clientService.delete(id);
        return "redirect:/managers/client";
    }
}
