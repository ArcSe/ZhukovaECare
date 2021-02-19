package com.javaschool.service.ipml;

import com.javaschool.dto.*;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.model.User;
import com.javaschool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ClientService clientService;
    private final ContractService contractService;
    private final TariffService tariffService;
    private final OptionService optionService;

    @Autowired
    public ShoppingCartServiceImpl(ClientService clientService, ContractService contractService,
                                TariffService tariffService, OptionService optionService) {
        this.clientService = clientService;
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    public ContractDto getContractDto(User user, ShoppingCartDto shoppingCart, Model model) {
        ContractDto contractDto = new ContractDto();
        contractDto.setClientId(user.getClient().getId());
        return contractDto;
    }

    public void buyContracts(Set<ContractShoppingCartDto> contracts) {
        buyExistContacts(contracts);
        buyNewContracts(contracts);
    }

    private void buyNewContracts(Set<ContractShoppingCartDto> contracts) {
        contracts.stream().filter(contract -> contract.getContract().getNumber().equals("new"))
                .forEach(contract -> {
                    try {
                        buyNewContracts(contract);
                    } catch (DataFormatException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void buyExistContacts(Set<ContractShoppingCartDto> contracts) {
        contracts.stream().filter(contract -> !contract.getContract().getNumber().equals("new"))
                .forEach(contractShoppingCartDto -> {
                    ContractDto contractDto = contractShoppingCartDto.getContract();
                    Set<OptionDto> options = contractDto.getOptions();
                    options.addAll(contractShoppingCartDto.getOptionsShoppingCart());
                    contractDto.setOptions(options);
                    contractService.update(contractDto);
                });
    }

    private void buyNewContracts(ContractShoppingCartDto contractShoppingCartDto) throws DataFormatException {
        ContractDto contractDto = contractShoppingCartDto.getContract();
        contractDto.setNumber(contractService.generatePhoneNumber());
        contractService.add(contractDto);
    }

    public void addNewContractToShoppingCart(ShoppingCartDto shoppingCart, long tariffId, ContractDto contract,
                                             Set<ContractShoppingCartDto> set, ContractShoppingCartDto contractShoppingCartDto) throws ExamplesNotFoundException {
        TariffDto tariff = addTariffToContract(tariffId, contract);
        addContractToContractWrapper(contract, contractShoppingCartDto, tariff);
        set.add(contractShoppingCartDto);
        shoppingCart.setContracts(set);
        increasePriceShoppingCart(shoppingCart, contractShoppingCartDto);
    }

    private void addContractToContractWrapper(ContractDto contract, ContractShoppingCartDto contractShoppingCartDto, TariffDto tariff) {
        contractShoppingCartDto.setContract(contract);
        contractShoppingCartDto.setOptionsShoppingCart(tariff.getOptions());
    }

    private TariffDto addTariffToContract(long tariffId, ContractDto contract) throws ExamplesNotFoundException {
        contract.setNumber("new");
        TariffDto tariff = tariffService.getById(tariffId);
        contract.setTariff(tariff);
        contract.setOptions(tariff.getOptions());
        return tariff;
    }

    public void refreshContractFromShoppingCart(ShoppingCartDto shoppingCart, long contractId) {
        ContractShoppingCartDto contractShoppingCartDto = shoppingCart.getContracts().stream()
                .filter(contractDto -> contractDto.getContract().getId() == contractId).findFirst().orElse(null);

        Set<ContractShoppingCartDto> newContracts = deleteOldContractFromShoppingCart(shoppingCart, contractId);
        shoppingCart.setContracts(newContracts);

        if (contractShoppingCartDto != null) {
            shoppingCart.setPrice(shoppingCart.getPrice() - contractShoppingCartDto.getPrice());
            shoppingCart.setServiceCost(shoppingCart.getServiceCost() - contractShoppingCartDto.getServiceCost());
        }
    }

    private Set<ContractShoppingCartDto> deleteOldContractFromShoppingCart(ShoppingCartDto shoppingCart, long contractId) {
        Set<ContractShoppingCartDto> oldContracts = shoppingCart.getContracts();
        Set<ContractShoppingCartDto> newContracts = oldContracts.stream()
                .filter(contract -> contract.getContract().getId() != contractId)
                .collect(Collectors.toSet());
        return newContracts;
    }

    public void removeOptionFromShoppingCart(ShoppingCartDto shoppingCart, long contractId, long optionId) throws ExamplesNotFoundException {
        ContractShoppingCartDto contractDto = shoppingCart.getContracts().stream()
                .filter(contract -> contract.getContract().getId() == contractId).findFirst().orElse(null);
        Set<OptionDto> options = null;
        if (contractDto != null) {
            options = contractDto.getOptionsShoppingCart();

            options = options.stream().filter(optionDto -> optionDto.getId() != optionId).collect(Collectors.toSet());
            contractDto.setOptionsShoppingCart(options);

            Set<ContractShoppingCartDto> newContracts = deleteOldContractFromShoppingCart(shoppingCart, contractId);
            decreaseContractPrice(contractDto, optionId);
            newContracts.add(contractDto);
            shoppingCart.setContracts(newContracts);
            decreaseShoppingCartPrice(shoppingCart, optionId);
        }
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


    private void addTariffToShoppingCartDto(User user, long tariffId,
                                            ContractShoppingCartDto contract, ShoppingCartDto shoppingCart,
                                            Set<ContractShoppingCartDto> setOfContracts) throws ExamplesNotFoundException {
        TariffDto tariff = tariffService.getById(tariffId);
        contract.getContract().setTariff(tariff);
        deleteOldTariffOptions(contract, tariff);
        if(!tariff.getOptions().isEmpty()) {
            tariff.getOptions().forEach(option-> {
                try {
                    addNewContractOptionToShoppingCart(option.getId(), contract);
                } catch (ExamplesNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
        increasePriceContract(contract);

        setOfContracts = setOfContracts.stream().filter(contractDto -> contractDto.getContract().getId() != contract.getId()).collect(Collectors.toSet());
        setOfContracts.add(contract);
        shoppingCart.setContracts(setOfContracts);
        if(!Objects.isNull(user)){
            shoppingCart.setCustomerEmail(user.getEmail());
        }

        increasePriceShoppingCartInService(shoppingCart);
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


    private ContractShoppingCartDto checkingContractDuplicate(ShoppingCartDto shoppingCart,long contractId ) throws ExamplesNotFoundException {
        Set<ContractShoppingCartDto> set = shoppingCart.getContracts();
        ContractDto contract = contractService.getById(contractId);
        ContractShoppingCartDto check = set.stream().filter(contractDto -> contractDto.getContract().getId() == contractId)
                .findFirst().orElse(null);
        if(check == null){
            check = new ContractShoppingCartDto();
            check.setContract(contract);
            check.setOptionsShoppingCart(new HashSet<>());
        }
        return check;
    }

    public void addOptionToShoppingCart(ShoppingCartDto shoppingCart, long optionId, long contractId) throws ExamplesNotFoundException {
        if (shoppingCart.getContracts() != null) {
            ContractShoppingCartDto contract = checkingContractDuplicate(shoppingCart, contractId);
            addNewContractOptionToShoppingCart(optionId, contract);
            /*
            Set<ContractShoppingCartDto> setOfContracts = shoppingCart.getContracts();
            setOfContracts = setOfContracts.stream().filter(contractDto -> contractDto.getContract().getId() != contractId).collect(Collectors.toSet());
            */
            Set<ContractShoppingCartDto> setOfContracts = deleteOldContractFromShoppingCart(shoppingCart, contractId);
            setOfContracts.add(contract);
            shoppingCart.setContracts(setOfContracts);
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

    public void increasePriceShoppingCart(ShoppingCartDto shoppingCart, ContractShoppingCartDto contract) {
        increasePriceContract(contract);
        increasePriceShoppingCartInService(shoppingCart);
    }

    private void increasePriceShoppingCartInService(ShoppingCartDto cart){
        int price = 0;
        int serviceCost = 0;
        for (ContractShoppingCartDto con :cart.getContracts()) {
            price += con.getPrice();
            serviceCost += con.getServiceCost();
        }
        cart.setPrice(price);
        cart.setServiceCost(serviceCost);
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

    public ShoppingCartDto addTariffToShopping(User user, ShoppingCartDto shoppingCart, long tariffId, long contractId) throws ExamplesNotFoundException {
        Set<ContractShoppingCartDto> set = new HashSet<>();
        ContractShoppingCartDto contract;
        if (shoppingCart.getContracts() != null ) {
            set = shoppingCart.getContracts();
            contract = checkingContractDuplicate(shoppingCart, contractId);
        } else {
            if(shoppingCart.getCustomerEmail() == null) {
                shoppingCart = new ShoppingCartDto();
            }
            contract = new ContractShoppingCartDto();
            contract.setContract(contractService.getById(contractId));
            contract.setOptionsShoppingCart(new HashSet<>());
        }
        addTariffToShoppingCartDto(user, tariffId, contract, shoppingCart, set);
        return shoppingCart;
    }
}
