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

    @GetMapping("/cart")
    public String getShoppingCart(@SessionAttribute(name = "shoppingCart", required=false) ShoppingCartDto cart,
                                  final Model model) {
        model.addAttribute("shoppingCart", cart);
        return "jsp/indexCart";
    }


}
