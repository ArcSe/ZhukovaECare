package com.javaschool.controller.crud;

import com.javaschool.dto.TariffDto;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }
    
    @RequestMapping("/tariff")
    public ModelAndView home() {
        List<TariffDto> listTariff = tariffService.getAll();
        ModelAndView mav = new ModelAndView("tariffs/home");
        mav.addObject("listTariff", listTariff);
        return mav;
    }

    @RequestMapping("tariffs/new")
    public String newTariff(Map<String, Object> model) {
        TariffDto tariff = new TariffDto();
        model.put("tariff", tariff);
        return "tariffs/new_tariff";
    }

    @RequestMapping(value = "tariffs/save", method = RequestMethod.POST)
    public String saveTariff(@ModelAttribute("tariff") TariffDto tariff) {
        tariffService.add(tariff);
        return "redirect:/";
    }
}
