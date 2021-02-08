package com.javaschool.controller.pages.client;

import com.javaschool.dto.ShoppingCartDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {
    @GetMapping("/cart")
    public String cart(@SessionAttribute(name = "shoppingCart", required=false) ShoppingCartDto cart, final Model model) {
        model.addAttribute("shoppingCart", cart);
        return "jsp/indexCart";
    }
}
