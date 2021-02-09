package com.javaschool.controller.pages.client;

import com.javaschool.dto.ContractDto;
import com.javaschool.dto.ContractShoppingCartDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.ShoppingCartDto;
import com.javaschool.service.ClientService;
import com.javaschool.service.ContractService;
import com.javaschool.service.OptionService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class ShoppingCartController {

    private final ClientService clientService;
    private final ContractService contractService;
    private final TariffService tariffService;
    private final OptionService optionService;

    @Autowired
    public ShoppingCartController(ClientService clientService, ContractService contractService,
                                TariffService tariffService, OptionService optionService) {
        this.clientService = clientService;
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    @GetMapping("/cart")
    public String getShoppingCart(@SessionAttribute(name = "shoppingCart", required=false) ShoppingCartDto cart, final Model model) {
        model.addAttribute("shoppingCart", cart);
        return "jsp/indexCart";
    }


}
