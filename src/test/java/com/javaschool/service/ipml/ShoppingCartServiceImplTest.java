package com.javaschool.service.ipml;

import com.javaschool.dao.impl.ContractDaoImpl;
import com.javaschool.dto.*;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.mapper.ContractMapper;
import com.javaschool.model.Option;
import com.javaschool.model.Role;
import com.javaschool.model.ShoppingCart;
import com.javaschool.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartServiceImplTest {
    @TestConfiguration
    static class ShoppingCartServiceTestConfiguration {
        @Bean
        public ShoppingCartServiceImpl userService() {
            return new ShoppingCartServiceImpl();
        }
    }

    @InjectMocks
    ShoppingCartServiceImpl shoppingCartService;

    @Mock
    TariffServiceImpl tariffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void increasePriceShoppingCart() {
        Option option1 = new Option("option1", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        OptionDto optionDto1 = new OptionDto("option1", 100, 100, new HashMap<>(), new HashMap<>());
        ContractShoppingCartDto contract = new ContractShoppingCartDto();
        contract.setOptionsShoppingCart(new HashSet<>(Collections.singletonList(optionDto1)));
        ShoppingCartDto cart = new ShoppingCartDto();
        cart.setContracts(new HashSet<>(Collections.singletonList(contract)));

        shoppingCartService.increasePriceShoppingCart(cart, contract);
        Assert.assertEquals(option1.getPrice(), contract.getPrice());
    }

}