package com.javaschool.controller.pages.client;

import com.javaschool.dto.ClientDto;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.ContractShoppingCartDto;
import com.javaschool.dto.ShoppingCartDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.model.User;
import com.javaschool.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/client")
@SessionAttributes("shoppingCart")
public class ClientPageController {

    private static final Logger logger = Logger.getLogger(ClientPageController.class);

    private final ClientService clientService;
    private final ContractService contractService;
    private final TariffService tariffService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ClientPageController(ClientService clientService, ContractService contractService,
                                TariffService tariffService,  ShoppingCartService shoppingCartService) {
        this.clientService = clientService;
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.shoppingCartService = shoppingCartService;
    }

    @ModelAttribute("shoppingCart")
    public ShoppingCartDto shoppingCart() {
        return new ShoppingCartDto();
    }

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @RequestMapping("/userProfile")
    public ModelAndView getPersonalPage(@AuthenticationPrincipal User user) throws Exception{
        ModelAndView mav;
        if(Objects.isNull(user.getClient())) {
            mav = new ModelAndView("jsp/client/userProfile/userProfile");
            mav.addObject("email", user.getEmail());
        }
        else {
            mav = new ModelAndView("jsp/client/userProfile/clientProfile");

            ClientDto client = clientService.getClientDtoForUserProfile(user);
            mav.addObject("client", client);
        }
        return mav;
    }

    @PostMapping("/cart")
    public String buy(final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart) {

        Set<ContractShoppingCartDto> contracts = shoppingCart.getContracts();
        shoppingCartService.buyContracts(contracts);
        model.addAttribute("shoppingCart", new ShoppingCartDto());
        return "redirect:/client/userProfile";
    }
    
    @RequestMapping("/contract")
    public ModelAndView createContract(@AuthenticationPrincipal User user,
                                       @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                       Model model) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/new_contract");
        if(!Objects.isNull(shoppingCart.getContracts())) {
            shoppingCart.getContracts().forEach(contract -> {
                if (contract.getContract().getNumber().equals("new")) {
                    model.addAttribute("newContractError", "Please, finish your order with another new contract at first");
                }
            });
        }
        ContractDto contractDto = shoppingCartService.getContractDto(user, shoppingCart, model);
        mav.addObject("contract",contractDto);
        mav.addObject("tariffs", tariffService.getAll());
//        mav.addObject("clientId",contractDto.getClientId() );
        return mav;
    }

    @PostMapping("/save")
    public String createContractToShoppingCart(final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                                @RequestParam("tariff.id") long tariffId,
                                               @ModelAttribute("contract") ContractDto contract) throws Exception{
        Set<ContractShoppingCartDto> set = new HashSet<>();
        ContractShoppingCartDto contractShoppingCartDto = new ContractShoppingCartDto();

        if (shoppingCart.getContracts() != null) {
            set = shoppingCart.getContracts();
            ContractShoppingCartDto contractShoppingCart = shoppingCart.getContracts().stream()
                    .filter(contractL -> contractL.getContract().getNumber().equals("new")).findFirst().orElse(null);
            if(contractShoppingCart != null){
                model.addAttribute("newContractError", "Please, finish your order with another new contract at first");
                return "redirect:/client/userProfile";
            }
            shoppingCart = new ShoppingCartDto();
        }

        shoppingCartService.addNewContractToShoppingCart(shoppingCart, tariffId, contract, set, contractShoppingCartDto);
        //shoppingCartService.increasePriceShoppingCart(shoppingCart, contractShoppingCartDto);
        model.addAttribute("shoppingCart", shoppingCart);
        return "redirect:/client/userProfile";
    }

    @RequestMapping("/contractInfo")
    public ModelAndView getContractById(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/contracts/contractInfoPage");
        mav.addObject("contract", contractService.getById(id));
        return mav;
    }

    @RequestMapping("/addTariff")
    public ModelAndView chooseTariff(@RequestParam long id,
                                     @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/client/userProfile/addTariff");
        createShoppingCartDtoSession(mav, shoppingCart);
        mav.addObject("contract", contractService.getById(id));
        mav.addObject("listTariff", tariffService.getAll());
        return mav;
    }

    @PostMapping("/removeTariff")
    public String removeContractFromCart(final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                       @RequestParam("contractId") long contractId) throws ExamplesNotFoundException {
        shoppingCartService.refreshContractFromShoppingCart(shoppingCart, contractId);
        model.addAttribute("shoppingCart", shoppingCart);
        return "redirect:/cart";
    }

    @PostMapping("/removeOption")
    public String removeOptionFromCart( @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                       @RequestParam("contractId") long contractId,
                                       @RequestParam("optionId") long optionId) throws ExamplesNotFoundException {
        shoppingCartService.removeOptionFromShoppingCart(shoppingCart, contractId, optionId);
        return "redirect:/cart";
    }

    @PostMapping("/addTariff")
    public String addTariffToCart(@AuthenticationPrincipal User user,
                            final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                            @RequestParam("tariffId") long tariffId,
                            @RequestParam("contractId") long contractId
                            ) throws Exception{
        shoppingCart = shoppingCartService.addTariffToShopping(user, shoppingCart, tariffId, contractId);
        model.addAttribute("shoppingCart", shoppingCart);

        return "redirect:/cart";
    }

   
    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    private String addOptionToShoppingCartDto(final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                           @RequestParam("optionId") long optionId,
                                           @RequestParam("contractId") long contractId) throws Exception {
        shoppingCartService.addOptionToShoppingCart(shoppingCart, optionId, contractId);
        model.addAttribute("shoppingCart", shoppingCart);
        return "redirect:/cart";
    }

    public static void createShoppingCartDtoSession(ModelAndView model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart){
        if (shoppingCart != null) {
            model.addObject("shoppingCart", shoppingCart);
        } else {
            model.addObject("shoppingCart", new ShoppingCartDto());
        }
    }

}
