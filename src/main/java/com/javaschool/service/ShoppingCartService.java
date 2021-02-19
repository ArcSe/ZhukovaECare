package com.javaschool.service;

import com.javaschool.dto.ContractDto;
import com.javaschool.dto.ContractShoppingCartDto;
import com.javaschool.dto.ShoppingCartDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.model.User;
import org.springframework.ui.Model;

import java.util.Set;


public interface ShoppingCartService {

    void buyContracts(Set<ContractShoppingCartDto> contracts);

    ContractDto getContractDto(User user, ShoppingCartDto shoppingCart, Model model);

    void addNewContractToShoppingCart(ShoppingCartDto shoppingCart, long tariffId, ContractDto contract,
                                      Set<ContractShoppingCartDto> set,
                                      ContractShoppingCartDto contractShoppingCartDto) throws ExamplesNotFoundException;

    void refreshContractFromShoppingCart(ShoppingCartDto shoppingCart, long contractId);

    void removeOptionFromShoppingCart(ShoppingCartDto shoppingCart, long contractId, long optionId) throws ExamplesNotFoundException;

    void addOptionToShoppingCart(ShoppingCartDto shoppingCart, long optionId, long contractId) throws ExamplesNotFoundException;

    ShoppingCartDto addTariffToShopping(User user, ShoppingCartDto shoppingCart, long tariffId, long contractId) throws ExamplesNotFoundException;
}
