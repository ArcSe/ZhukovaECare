package com.javaschool.controller.pages.manager;

import com.javaschool.controller.pages.ControllerUtils;
import com.javaschool.dto.ClientDto;
import com.javaschool.dto.UserDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/managers/client")
public class ClientController {

    private final ClientService clientService;
    private final ContractService contractService;

    @Autowired
    public ClientController(ClientService clientService, ContractService contractService) {
        this.clientService = clientService;
        this.contractService = contractService;
    }

    @RequestMapping()
    public ModelAndView getAll() throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/clients/clientsList");
        mav.addObject("listClient", clientService.getAll());
        return mav;
    }

    @RequestMapping( method = RequestMethod.POST)
    public ModelAndView getAllByQuery(@RequestParam String phone) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/clients/clientsList");
        mav.addObject("listClient", clientService.getAllByQuery(phone));
        return mav;
    }

    @RequestMapping("/getById")
    public ModelAndView getById(@RequestParam long id) throws Exception {
        ModelAndView mav = new ModelAndView("jsp/managers/clients/clientInfoPage");
        mav.addObject("client", clientService.getById(id));
        return mav;
    }

    @RequestMapping("new")
    public String newClient(Map<String, Object> model) throws Exception{
        model.put("client", new ClientDto());
        return "jsp/managers/clients/new_client";
    }

    @RequestMapping("/addContract")
    public ModelAndView editContract(@RequestParam long id) throws Exception {
        ModelAndView mav = new ModelAndView("jsp/managers/clients/addContract");
        mav.addObject("client", clientService.getById(id));
        mav.addObject("contracts", contractService.getAll());
        return mav;
    }

    @RequestMapping(value = "/addContract", method = RequestMethod.POST)
    private String addContract(@RequestParam("clientId") long clientId,
                                      @RequestParam("contractId") long contractId) throws Exception{
        clientService.addContract(clientId, contractId);
        editContract(clientId);
        return "redirect:/managers/client/addContract?id="+ clientId;
    }

    @RequestMapping(value ="/deleteContract", method = RequestMethod.POST)
    public String  deleteContract(@RequestParam("clientId") long clientId,
                                         @RequestParam("contractId") long contractId) throws Exception {
        clientService.deleteContracts(clientId, contractId);
        return "redirect:/managers/client/addContract?id="+ clientId;
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String saveClient(@Valid @ModelAttribute("client") ClientDto client,
                             BindingResult bindingResult, Model model,
                             @RequestParam("calendar") String calendar) throws Exception{
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            client.setBirthday(calendar);
            model.addAttribute("client", client);

            return "jsp/managers/clients/new_client";
        }

        if(calendar.equals("")){
            model.addAttribute("birthdayError", "Birthday shouldn't be empty");
            model.addAttribute("client", client);
            return "jsp/managers/clients/new_client";
        }
        if(!clientService.add(client)){
            model.addAttribute("passportError", "Client's passport already exist");
            model.addAttribute("client", client);
            return "jsp/managers/clients/new_client";
        }
        client.setBirthday(calendar);
        clientService.add(client);
        return "redirect:/managers/client";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String updateClient(@Valid @ModelAttribute("client") ClientDto client,
                               BindingResult bindingResult, Model model,
                               @RequestParam("calendar") String calendar) throws Exception{
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            client.setBirthday(calendar);
            model.addAttribute("client", client);

            return "jsp/managers/clients/edit_client";
        }

        if(calendar.equals("")){
            model.addAttribute("birthdayError", "Birthday shouldn't be empty");
            model.addAttribute("client", client);
            return "jsp/managers/clients/edit_client";
        }
        System.out.println(client);
        client.setBirthday(calendar);
        clientService.update(client);
        return "redirect:/managers/client";
    }

    @RequestMapping("edit")
    public ModelAndView editClient(@RequestParam long id) throws Exception {
        ModelAndView mav = new ModelAndView("jsp/managers/clients/edit_client");
        ClientDto client = clientService.getById(id);
        mav.addObject("client", client);
        return mav;
    }

    @RequestMapping(value = "delete")
    public String deleteClientById(@RequestParam long id) throws Exception{
        clientService.delete(id);
        return "redirect:/managers/client";
    }
}
