package com.javaschool.controller.pages.client;

import com.javaschool.dto.ShoppingCartDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ShoppingCartController {

    @GetMapping("/cart")
    public String getShoppingCart(@SessionAttribute(name = "shoppingCart", required=false) ShoppingCartDto cart,
                                  final Model model) {
        model.addAttribute("shoppingCart", cart);
        return "jsp/indexCart";
    }

    @GetMapping("/shoppingList")
    public String getShoppingCartForManager(@SessionAttribute(name = "shoppingCartForManager", required=false) ShoppingCartDto cart,
                                  final Model model) {
        model.addAttribute("shoppingCartForManager", cart);
        return "jsp/shoppingCart";
    }

}
