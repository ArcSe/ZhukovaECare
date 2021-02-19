package com.javaschool.controller.pages.manager;

import com.javaschool.dto.*;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.model.User;
import com.javaschool.service.*;
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
    private final OptionService optionService;
    private final ClientService clientService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ContractController(ContractService contractService, TariffService tariffService, OptionService optionService, ClientService clientService, ShoppingCartService shoppingCartService) {
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.clientService = clientService;
        this.shoppingCartService = shoppingCartService;
    }

    @ModelAttribute("shoppingCartForManager")
    public ShoppingCartDto shoppingCart() {
        return new ShoppingCartDto();
    }

    @RequestMapping()
    public ModelAndView getAllContracts() throws Exception{
        List<ContractDto> contracts = contractService.getAll();
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/contractList");
        mav.addObject("contracts", contracts);
        return mav;
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

    /////////////////////////////////////////

    @PostMapping("/shoppingList")
    public String buy(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart) {

        Set<ContractShoppingCartDto> contracts = shoppingCart.getContracts();
        shoppingCartService.buyContracts(contracts);
        model.addAttribute("shoppingCartForManager", new ShoppingCartDto());
        return "redirect:/managers/contracts";
    }

    @RequestMapping("/new")
    public ModelAndView createContract(@AuthenticationPrincipal User user,
                                       @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
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

        if (shoppingCart.getContracts() != null) {
            set = shoppingCart.getContracts();
            ContractShoppingCartDto contractShoppingCart = shoppingCart.getContracts().stream()
                    .filter(contractL -> contractL.getContract().getNumber().equals("new")).findFirst().orElse(null);
            if(contractShoppingCart != null){
                model.addAttribute("newContractError", "Please, finish your order with another new contract at first");
                return "redirect:/managers/contracts";
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
        return mav;
    }

    @PostMapping("/removeTariff")
    public String removeContractFromCart(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                         @RequestParam("contractId") long contractId) throws ExamplesNotFoundException {
        shoppingCartService.refreshContractFromShoppingCart(shoppingCart, contractId);
        model.addAttribute("shoppingCartForManager", shoppingCart);
        return "redirect:/shoppingList";
    }

    @PostMapping("/removeOption")
    public String removeOptionFromCart( @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                        @RequestParam("contractId") long contractId,
                                        @RequestParam("optionId") long optionId) throws ExamplesNotFoundException {
        shoppingCartService.removeOptionFromShoppingCart(shoppingCart, contractId, optionId);
        return "redirect:/shoppingList";
    }

    @PostMapping("/addTariff")
    public String addTariffToCart(@AuthenticationPrincipal User user,
                                  final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                  @RequestParam("tariffId") long tariffId,
                                  @RequestParam("contractId") long contractId) throws Exception{
        shoppingCart = shoppingCartService.addTariffToShopping(user, shoppingCart, tariffId, contractId);
        model.addAttribute("shoppingCartForManager", shoppingCart);

        return "redirect:/shoppingList";
    }


    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    private String addOptionToShoppingCartDto(final Model model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart,
                                              @RequestParam("optionId") long optionId,
                                              @RequestParam("contractId") long contractId) throws Exception {
        shoppingCartService.addOptionToShoppingCart(shoppingCart, optionId, contractId);
        model.addAttribute("shoppingCartForManager", shoppingCart);
        return "redirect:/shoppingList";
    }

    public static void createShoppingCartDtoSession(ModelAndView model, @ModelAttribute("shoppingCartForManager") ShoppingCartDto shoppingCart){
        if (shoppingCart != null) {
            model.addObject("shoppingCartForManager", shoppingCart);
        } else {
            model.addObject("shoppingCartForManager", new ShoppingCartDto());
        }
    }

}
