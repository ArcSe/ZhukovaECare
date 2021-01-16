package com.javaschool.controller.pages;

import com.javaschool.dao.ClientDao;
import com.javaschool.dto.ClientDto;
import com.javaschool.mapper.ClientMapper;
import com.javaschool.model.Client;
import com.javaschool.model.Role;
import com.javaschool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private ClientService clientService;
    private ClientMapper mapper;

    @GetMapping("/auth/registration")
    public String registration(Map<String, Object> model){
        Client client = new Client();
        model.put("client", client);
        return "jsp/auth/registration";
    }

    @PostMapping("/auth/registration")
    public String addClient(@ModelAttribute("client") Client client, Map<String, Object> model) {
 /*
        Client clientFromDb = clientService.getByClientEmail(client.getEmail());

        if (clientFromDb != null) {
            model.put("message", "Client exists!");
            return "redirect:/login";
        }
*/
        client.setActive(true);
        client.setRoles(Collections.singleton(Role.USER));
        System.out.println(client.getEmail() + " " + client.getPassword() + " " + client.isActive());
        clientService.save(client);

        return "redirect:/login";
    }
}
