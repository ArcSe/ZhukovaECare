package com.javaschool.controller.pages.client;

import com.javaschool.dto.*;
import com.javaschool.exception.ExceptionHandlerController;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.model.User;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import com.javaschool.service.OptionService;
import com.javaschool.service.TariffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Controller
@RequestMapping("/client")
@SessionAttributes("shoppingCart")
public class ClientPageController {

    private static final Logger logger = Logger.getLogger(ClientPageController.class);

    private final ClientService clientService;
    private final ContractService contractService;
    private final TariffService tariffService;
    private final OptionService optionService;

    @Autowired
    public ClientPageController(ClientService clientService, ContractService contractService,
                                TariffService tariffService, OptionService optionService) {
        this.clientService = clientService;
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    @ModelAttribute("shoppingCart")
    public ShoppingCartDto shoppingCart() {
        return new ShoppingCartDto();
    }

    private void buyNewContracts(ContractShoppingCartDto contractShoppingCartDto) throws DataFormatException {
        ContractDto contractDto = contractShoppingCartDto.getContract();
        contractDto.setNumber(contractService.generatePhoneNumber());
        contractService.add(contractDto);
    }

    @PostMapping("/cart")
    public String buy(final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart) {

        Set<ContractShoppingCartDto> contracts = shoppingCart.getContracts();
        contracts.stream().filter(contract -> !contract.getContract().getNumber().equals("new"))
                .forEach(contractShoppingCartDto -> {
                    ContractDto contractDto = contractShoppingCartDto.getContract();
                    Set<OptionDto> options = contractDto.getOptions();
                    options.addAll(contractShoppingCartDto.getOptionsShoppingCart());
                    contractDto.setOptions(options);
                    contractService.update(contractDto);
                });
        contracts.stream().filter(contract -> contract.getContract().getNumber().equals("new"))
                .forEach(contract -> {
                    try {
                        buyNewContracts(contract);
                    } catch (DataFormatException e) {
                        e.printStackTrace();
                    }
                });
        model.addAttribute("shoppingCart", new ShoppingCartDto());
        return "redirect:/client/userProfile";
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

            long clientId = user.getClient().getId();
            ClientDto client = clientService.getById(clientId);
            mav.addObject("client", client);
        }
        return mav;
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
        ContractDto contractDto = new ContractDto();
        contractDto.setClientId(user.getClient().getId());
        mav.addObject("contract",contractDto);
        mav.addObject("tariffs", tariffService.getAll());
        mav.addObject("clientId",contractDto.getClientId() );
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
        contract.setNumber("new");
        TariffDto tariff = tariffService.getById(tariffId);
        contract.setTariff(tariff);
        contract.setOptions(tariff.getOptions());
        contractShoppingCartDto.setContract(contract);
        contractShoppingCartDto.setOptionsShoppingCart(tariff.getOptions());
        set.add(contractShoppingCartDto);
        shoppingCart.setContracts(set);
        increasePriceShoppingCart(shoppingCart, contractShoppingCartDto);
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
        ContractShoppingCartDto contractShoppingCartDto = checkingContractDuplicate(shoppingCart, contractId);
        Set<ContractShoppingCartDto> oldContracts = shoppingCart.getContracts();
        Set<ContractShoppingCartDto> newContracts = oldContracts.stream()
                .filter(contract -> contract.getContract().getId() != contractId )
                .collect(Collectors.toSet());
        shoppingCart.setContracts(newContracts);
        shoppingCart.setPrice(shoppingCart.getPrice()-contractShoppingCartDto.getPrice());
        shoppingCart.setServiceCost(shoppingCart.getServiceCost()-contractShoppingCartDto.getServiceCost());
        model.addAttribute("shoppingCart", shoppingCart);
        return "redirect:/cart";
    }

    @PostMapping("/removeOption")
    public String removeOptionFromCart(final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                       @RequestParam("contractId") long contractId,
                                       @RequestParam("optionId") long optionId) throws ExamplesNotFoundException {
        ContractShoppingCartDto contract = checkingContractDuplicate(shoppingCart,contractId);
        Set<OptionDto> options = contract.getOptionsShoppingCart();
        options = options.stream().filter(optionDto -> optionDto.getId() != optionId).collect(Collectors.toSet());
        contract.setOptionsShoppingCart(options);
        Set<ContractShoppingCartDto> oldContracts = shoppingCart.getContracts();
        Set<ContractShoppingCartDto> newContracts = oldContracts.stream()
                .filter(contractDto -> contractDto.getContract().getId() != contractId )
                .collect(Collectors.toSet());
        decreaseContractPrice(contract, optionId);
        newContracts.add(contract);
        shoppingCart.setContracts(newContracts);
        decreaseShoppingCartPrice(shoppingCart, optionId);
        return "redirect:/cart";
    }

    private void decreaseContractPrice(ContractShoppingCartDto contract, long optionId) throws ExamplesNotFoundException {
        int price = contract.getPrice();
        int serviceCost = contract.getServiceCost();
        OptionDto optionDto = optionService.getById(optionId);
        contract.setPrice(price-optionDto.getPrice());
        contract.setServiceCost(serviceCost-optionDto.getServiceCost());
    }

    private void decreaseShoppingCartPrice(ShoppingCartDto shoppingCart, long optionId) throws ExamplesNotFoundException {
        int price = shoppingCart.getPrice();
        int serviceCost = shoppingCart.getServiceCost();
        OptionDto optionDto = optionService.getById(optionId);
        shoppingCart.setPrice(price-optionDto.getPrice());
        shoppingCart.setServiceCost(serviceCost-optionDto.getServiceCost());
    }

    @PostMapping("/addTariff")
    public String addTariffToCart(@AuthenticationPrincipal User user,
                            final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                            @RequestParam("tariffId") long tariffId,
                            @RequestParam("contractId") long contractId
                            ) throws Exception{

        logger.debug(shoppingCart);
        Set<ContractShoppingCartDto> set = new HashSet<>();
        ContractShoppingCartDto contract;
        if (shoppingCart.getContracts() != null) {
            set = shoppingCart.getContracts();
            contract = checkingContractDuplicate(shoppingCart,contractId);
            /*
            TariffDto tariff = tariffService.getById(tariffId);
            if(contract.getContract().getTariff().equals(tariff)){
                ShoppingCartDto finalShoppingCart = shoppingCart;
                tariff.getOptions().forEach(o-> {
                    try {
                        decreaseContractPrice(contract,o.getId());
                        decreaseShoppingCartPrice(finalShoppingCart,o.getId());
                    } catch (ExamplesNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
*/
        } else {
            shoppingCart = new ShoppingCartDto();
            contract = new ContractShoppingCartDto();
            contract.setContract(contractService.getById(contractId));
            contract.setOptionsShoppingCart(new HashSet<>());
        }
        addTariffToShoppingCartDto(user,  tariffId, contract, shoppingCart, set);

        model.addAttribute("shoppingCart", shoppingCart);

        return "redirect:/cart";
    }

    //todo перенести в серверы
    private void addTariffToShoppingCartDto( User user, long tariffId,
                                          ContractShoppingCartDto contract, ShoppingCartDto cart,
                                          Set<ContractShoppingCartDto> set) throws ExamplesNotFoundException {
        TariffDto tariff = tariffService.getById(tariffId);
        contract.getContract().setTariff(tariff);
        /*
        int price = contract.getPrice();
        int serviceCost = contract.getServiceCost();
        contract.setPrice(price+ tariff.getPrice());
        contract.setServiceCost(serviceCost+ tariff.getServiceCost());

         */
        deleteOldTariffOptions(contract, tariff);
        if(!tariff.getOptions().isEmpty()) {
            tariff.getOptions().forEach(o-> {
                try {
                    addNewContractOptionToShoppingCart(o.getId(), contract);
                } catch (ExamplesNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
        increasePriceContract(contract);
        set = set.stream().filter(contractDto -> contractDto.getContract().getId() != contract.getId()).collect(Collectors.toSet());
        set.add(contract);
        cart.setContracts(set);
        cart.setCustomerEmail(user.getEmail());
        increasePriceShoppingCartTariff(cart);
    }

    private void deleteOldTariffOptions(ContractShoppingCartDto contract, TariffDto tariff) {
        TariffDto oldTariff = contract.getContract().getTariff();
        Set<OptionDto> options = new HashSet<>();
        if(!tariff.getOptions().isEmpty()) {
            options = contract.getOptionsShoppingCart().stream().filter(optionDto -> !oldTariff.getOptions()
                    .contains(optionDto)).collect(Collectors.toSet());
        }
        contract.setOptionsShoppingCart(options);
    }

    private void increasePriceShoppingCartTariff(ShoppingCartDto cart){
        int price = 0;
        int serviceCost = 0;
        for (ContractShoppingCartDto con :cart.getContracts()) {
            price += con.getPrice();
            serviceCost += con.getServiceCost();
        }
        cart.setPrice(price);
        cart.setServiceCost(serviceCost);
    }


//todo перенести в серверы
    private ContractShoppingCartDto checkingContractDuplicate(ShoppingCartDto shoppingCart,long contractId ) throws ExamplesNotFoundException {
        Set<ContractShoppingCartDto> set = shoppingCart.getContracts();
        ContractDto contract = contractService.getById(contractId);
        ContractShoppingCartDto check = set.stream().filter(contractDto -> contractDto.getContract().getId() == contractId).findFirst().orElse(null);
        if(check == null){
            check = new ContractShoppingCartDto();
            check.setContract(contract);
            check.setOptionsShoppingCart(new HashSet<>());
        }
        return check;
    }
    private void addNewContractOptionToShoppingCart(long optionId, ContractShoppingCartDto contract) throws ExamplesNotFoundException {
        Set<OptionDto> options = new HashSet<>();
        if(!Objects.isNull(contract.getOptionsShoppingCart())){
            options = contract.getOptionsShoppingCart();
        }
        OptionDto option = optionService.getById(optionId);
        options.add(option);
        contract.setOptionsShoppingCart(options);
    }
    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    private String addOptionToShoppingCartDto(final Model model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart,
                                           @RequestParam("optionId") long optionId,
                                           @RequestParam("contractId") long contractId) throws Exception {
        if (shoppingCart.getContracts() != null) {
            ContractShoppingCartDto contract = checkingContractDuplicate(shoppingCart, contractId);
            addNewContractOptionToShoppingCart(optionId, contract);
            Set<ContractShoppingCartDto> set = shoppingCart.getContracts();
            set = set.stream().filter(contractDto -> contractDto.getContract().getId() != contractId).collect(Collectors.toSet());
            set.add(contract);
            shoppingCart.setContracts(set);
            increasePriceShoppingCart(shoppingCart, contract);

        }
         else {
            Set<ContractShoppingCartDto> set = new HashSet<>();
            ContractDto contract = contractService.getById(contractId);
            ContractShoppingCartDto contractShoppingCartDto = new ContractShoppingCartDto();
            contractShoppingCartDto.setContract(contract);
            addNewContractOptionToShoppingCart(optionId, contractShoppingCartDto);
            set.add(contractShoppingCartDto);
            shoppingCart.setContracts(set);
            increasePriceShoppingCart(shoppingCart, contractShoppingCartDto);
        }
        model.addAttribute("shoppingCart", shoppingCart);
        return "redirect:/cart";
    }

    private void increasePriceShoppingCart(ShoppingCartDto shoppingCart, ContractShoppingCartDto contract) {
        increasePriceContract(contract);
        increasePriceShoppingCartTariff(shoppingCart);
    }

    private void increasePriceContract(ContractShoppingCartDto contract) {
        int price = 0;
        int serviceCost = 0;
        for (OptionDto con : contract.getOptionsShoppingCart()) {
            price += con.getPrice();
            serviceCost += con.getServiceCost();
        }
        contract.setPrice(price);
        contract.setServiceCost(serviceCost);
    }

    public static void createShoppingCartDtoSession(ModelAndView model, @ModelAttribute("shoppingCart") ShoppingCartDto shoppingCart){
        if (shoppingCart != null) {
            model.addObject("shoppingCart", shoppingCart);
        } else {
            model.addObject("shoppingCart", new ShoppingCartDto());
        }
    }

    /*
    @RequestMapping(value = "/addTariffToDB", method = RequestMethod.POST)
    private String addTariffToDB(@RequestParam("tariffId") long tariffId,
                             @RequestParam("contractId") long contractId) throws Exception {
        ContractDto contractDto = contractService.getById(contractId);
        TariffDto tariffDto = tariffService.getById(tariffId);
        contractDto.setTariff(tariffDto);
        contractService.update(contractDto);
        return "redirect:/client/addTariff?id="+ contractId;
    }
*/

}
