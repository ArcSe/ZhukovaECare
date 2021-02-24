package com.javaschool.controller.pages.manager;

import com.javaschool.controller.pages.client.ClientPageController;
import com.javaschool.dto.*;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.model.User;
import com.javaschool.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/managers/contracts")
@SessionAttributes("shoppingCartForManager")
public class ContractController {

    private final ContractService contractService;
    private final TariffService tariffService;
    private final ClientService clientService;
    private final ShoppingCartService shoppingCartService;

    private static final Logger logger = Logger.getLogger(ContractController.class);


    @Autowired
    public ContractController(ContractService contractService, TariffService tariffService,  ClientService clientService, ShoppingCartService shoppingCartService) {
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.clientService = clientService;
        this.shoppingCartService = shoppingCartService;
    }

    @ModelAttribute("shoppingCartForManager")
    public ShoppingCartDto shoppingCart() {
        return new ShoppingCartDto();
    }

    @RequestMapping()
    public String getAllContracts(Model model) throws Exception{
        List<ContractDto> contracts = contractService.getAll();
        model.addAttribute("contracts", contracts);
        return "jsp/managers/contracts/contractList";
    }

    @RequestMapping("/getById")
    public ModelAndView getById(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/contractInfoPage");
        mav.addObject("contract", contractService.getById(id));
        return mav;
    }
/*
    @RequestMapping("/new")
    public String newContract(Map<String, Object> model) throws Exception{
        ContractDto contractDto = new ContractDto();
        contractDto.setNumber(contractService.generatePhoneNumber());
        List<TariffDto> tariffs = tariffService.getAll();
        model.put("tariffs", tariffs);
        model.put("contract", contractDto);
        return "jsp/managers/contracts/new_contract";
    }

 */
/*
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveContract(@RequestParam("tariff.id") long tariffsId,
                               @ModelAttribute("contract") ContractDto contract) throws Exception {
        contract.setTariff(tariffService.getById(tariffsId));
        contractService.add(contract);
        return "redirect:/managers/contracts";
    }

    @RequestMapping(value ="/delete", method = RequestMethod.POST)
    public String deleteContractById(@RequestParam long id) throws Exception{
        contractService.delete(id);
        return "redirect:/managers/contracts";
    }

 */

    @RequestMapping(value ="/addClient")
    public ModelAndView addClient(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/add_client");
        ContractDto contractDto = contractService.getById(id);
        List<ClientDto> clientDtos = clientService.getAll();
        mav.addObject("clients", clientDtos);
        mav.addObject("contract", contractDto);
        return mav;
    }

    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    public String updateClients(@RequestParam("contract.id") long contractId,
                                @RequestParam("client.id") long clientId) throws Exception{
        ContractDto contractDto = contractService.getById(contractId);
        contractDto.setClientId(clientId);
        contractService.update(contractDto);
        return "redirect:/managers/contracts";
    }
/*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateTariff(@RequestParam("tariff.id") long tariffsId,
                               @ModelAttribute("contract") ContractDto contract) throws Exception {
        ContractDto contractDB = contractService.getById(contract.getId());
        contractDB.setTariff(tariffService.getById(tariffsId));
        contractDB.setClientId(contract.getClientId());
        contractDB.setNumber(contract.getNumber());
        contractService.update(contractDB);
        return "redirect:/managers/contracts";
    }

    @RequestMapping("/edit")
    public ModelAndView editTariff(@RequestParam long id) throws Exception {
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/edit_contract");
        ContractDto contractDto = contractService.getById(id);
        List<TariffDto> tariffs = tariffService.getAll();
        mav.addObject("tariff", tariffs);
        mav.addObject("contract", contractDto);
        return mav;
    }

 */

    @PostMapping("/shoppingList")
    public String buy(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart) {

        Set<ContractShoppingCartDto> contracts = shoppingCart.getContracts();
        shoppingCartService.buyContracts(contracts);
        model.addAttribute("shoppingCartForManager", new ShoppingCartDto());
        return "redirect:/managers/contracts";
    }

    @RequestMapping("/new")
    public ModelAndView createContract(@ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                       Model model) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/new_contract");
        if(!Objects.isNull(shoppingCart.getContracts())) {
            shoppingCart.getContracts().forEach(contract -> {
                if (contract.getContract().getNumber().equals("new")) {
                    model.addAttribute("newContractError", "Please, finish your order with another new contract at first");
                }
            });
        }
        ContractDto contractDto = new ContractDto();
        mav.addObject("contract",contractDto);
        mav.addObject("tariffs", tariffService.getAll());
        mav.addObject("clients",clientService.getAll());
        return mav;
    }

    @PostMapping("/save")
    public String createContractToShoppingCart(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                               @RequestParam("tariff.id") long tariffId,
                                               @RequestParam("client.id") long clientId,
                                               @ModelAttribute("contract") ContractDto contract) throws Exception{
        Set<ContractShoppingCartDto> set = new HashSet<>();
        ContractShoppingCartDto contractShoppingCartDto = new ContractShoppingCartDto();
        ClientDto clientDto = clientService.getById(clientId);
        if(addClientEmail(model, shoppingCart, clientId)){
            return "jsp/managers/contracts/contractList";
        }

        if (shoppingCart.getContracts() != null) {
            set = shoppingCart.getContracts();
            ContractShoppingCartDto contractShoppingCart = shoppingCart.getContracts().stream()
                    .filter(contractL -> contractL.getContract().getNumber().equals("new")).findFirst().orElse(null);
            if(contractShoppingCart != null){
                model.addAttribute("newContractError", "Please, finish your order with another new contract at first");
                return "jsp/managers/contracts/contractList";
            }
            shoppingCart = new ShoppingCartDto();
        }
        contract.setClientId(clientId);
        shoppingCart.setCustomerEmail(clientService.getById(clientId).getEmail());
        shoppingCartService.addNewContractToShoppingCart(shoppingCart, tariffId, contract, set, contractShoppingCartDto);
        //shoppingCartService.increasePriceShoppingCart(shoppingCart, contractShoppingCartDto);
        model.addAttribute("shoppingCartForManager", shoppingCart);
        return "redirect:/managers/contracts";
    }

    @RequestMapping("/contractInfo")
    public ModelAndView getContractById(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/contractInfoPage");
        mav.addObject("contract", contractService.getById(id));
        return mav;
    }

    @RequestMapping("/addTariff")
    public ModelAndView chooseTariff(@RequestParam long id,
                                     @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/edit_contract");
        createShoppingCartDtoSession(mav, shoppingCart);
        mav.addObject("contract", contractService.getById(id));
        mav.addObject("listTariff", tariffService.getAll());
        mav.addObject("shoppingCartForManager", shoppingCart);
        return mav;
    }

    @PostMapping("/removeTariff")
    public String removeContractFromCart(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                         @RequestParam("contractId") long contractId) throws ExamplesNotFoundException {
        shoppingCartService.refreshContractFromShoppingCart(shoppingCart, contractId);
        if(shoppingCart.getContracts().isEmpty()){
            shoppingCart.setCustomerEmail(null);
        }
        model.addAttribute("shoppingCartForManager", shoppingCart);
        return "redirect:/shoppingList";
    }

    @PostMapping("/removeOption")
    public String removeOptionFromCart( @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                        @RequestParam("contractId") long contractId,
                                        @RequestParam("optionId") long optionId) throws ExamplesNotFoundException {
        shoppingCartService.removeOptionFromShoppingCart(shoppingCart, contractId, optionId);
        if(shoppingCart.getContracts().isEmpty()){
            shoppingCart.setCustomerEmail(null);
        }
        return "redirect:/shoppingList";
    }

    @PostMapping("/addTariff")
    public String addTariffToCart(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                  @RequestParam("tariffId") long tariffId,
                                  @RequestParam("id") long contractId,
                                  @RequestParam("clientId") long clientId) throws Exception{
        if(clientId==0){
            model.addAttribute("notFoundClient", "Contract don't have a client");
            model.addAttribute("contracts", contractService.getAll());
            return "jsp/managers/contracts/contractList";
        }
        if(addClientEmail(model, shoppingCart, clientId)){
            return "jsp/managers/contracts/contractList";
        }
        shoppingCart = shoppingCartService.addTariffToShopping(null, shoppingCart, tariffId, contractId);
        model.addAttribute("shoppingCartForManager", shoppingCart);

        return "redirect:/shoppingList";
    }


    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    private String addOptionToShoppingCartDto(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                              @RequestParam("optionId") long optionId,
                                              @RequestParam("contractId") long contractId,
                                              @RequestParam("clientId") long clientId) throws Exception {

        if(clientId==0){
            model.addAttribute("notFoundClient", "Contract don't have a client");
            model.addAttribute("contracts", contractService.getAll());
            return "jsp/managers/contracts/contractList";
        }
        ClientDto clientDto = clientService.getById(clientId);
        String customerEmail = shoppingCart.getCustomerEmail();
        if(customerEmail != null && !customerEmail.equals(clientDto.getEmail())){
            model.addAttribute("differentClientError", "Please, finish your order with another client at first");
            List<ContractDto> contracts = contractService.getAll();
            model.addAttribute("contracts", contracts);
            return "jsp/managers/contracts/contractList";
        }
        if(customerEmail == null){
            shoppingCart.setCustomerEmail(clientDto.getEmail());
        }
        shoppingCartService.addOptionToShoppingCart(shoppingCart, optionId, contractId);
        model.addAttribute("shoppingCartForManager", shoppingCart);
        return "redirect:/shoppingList";
    }

    private boolean addClientEmail(Model model, ShoppingCartDto shoppingCart, long clientId) throws ExamplesNotFoundException, NotDataFoundException {
        ClientDto clientDto = clientService.getById(clientId);
        if(shoppingCart.getCustomerEmail() == null){
            shoppingCart.setCustomerEmail(clientDto.getEmail());
        }
         if(!shoppingCart.getCustomerEmail().equals(clientDto.getEmail())){
             model.addAttribute("differentClientError", "Please, finish your order with another client at first");
             List<ContractDto> contracts = contractService.getAll();
             model.addAttribute("contracts", contracts);
            return true;
        }
        return false;
    }

    public static void createShoppingCartDtoSession(ModelAndView model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart){
        if (shoppingCart != null) {
            model.addObject("shoppingCartForManager", shoppingCart);
        } else {
            model.addObject("shoppingCartForManager", new ShoppingCartDto());
        }
    }

}
