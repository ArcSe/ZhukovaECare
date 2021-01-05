package com.javaschool.controller.crud;

import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.service.OptionService;
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
    private final OptionService optionService;

    @Autowired
    public TariffController(TariffService tariffService, OptionService optionService) {
        this.tariffService = tariffService;
        this.optionService = optionService;
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
        List<OptionDto> options = optionService.getAll();
        model.put("tariff", tariff);
        model.put("options", options);
        return "tariffs/new_tariff";
    }

    @RequestMapping(value = "tariffs/save", method = RequestMethod.POST)
    public String saveTariff(@ModelAttribute("tariff") TariffDto tariff) {
        tariffService.add(tariff);
        return "redirect:/";
    }
}
