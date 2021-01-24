package com.javaschool.controller.pages.manager;

import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.service.OptionService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/managers/tariffs")
public class TariffController {
    private final TariffService tariffService;
    private final OptionService optionService;

    @Autowired
    public TariffController(TariffService tariffService, OptionService optionService) {
        this.tariffService = tariffService;
        this.optionService = optionService;
    }
    
    @RequestMapping()
    public ModelAndView home() {
        List<TariffDto> listTariff = tariffService.getAll();
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/tariffList");
        mav.addObject("listTariff", listTariff);
        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateOption(@RequestParam("option.id") long id,
                               @ModelAttribute("tariff") TariffDto tariff) {
        tariffService.update(id, tariff);
        return "redirect:/managers/tariffs";
    }

    @RequestMapping("/edit")
    public ModelAndView editOption(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/edit_tariff");
        TariffDto tariffDto = tariffService.getById(id);
        List<OptionDto> options = optionService.getAll();
        mav.addObject("tariff", tariffDto);
        mav.addObject("options", options);
        return mav;
    }

    @RequestMapping("/new")
    public String newTariff(Map<String, Object> model) {
        List<OptionDto> options = optionService.getAll();
        model.put("tariff", new TariffDto());
        model.put("options", options);
        return "jsp/managers/tariffs/new_tariff";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTariff(@RequestParam("option.id") long id, @ModelAttribute("tariff") TariffDto tariff) {
        OptionDto optionDto = optionService.getById(id);
        if(null==tariff.getOptions()){
            Set<OptionDto> set = new HashSet<>();
            set.add(optionDto);
            tariff.setOptions(set);
        }
        else {
            tariff.getOptions().add(optionDto);
        }
        tariffService.add(tariff);
        return "redirect:/managers/tariffs";
    }

    @RequestMapping("/editOptions")
    public ModelAndView editMandatoryOptions(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/addOptions");
        mav.addObject("tariff", tariffService.getById(id));
        mav.addObject("options", optionService.getAll());
        return mav;
    }
/*
    @RequestMapping(value = "/updateOptions", method = RequestMethod.POST)
    public String updateMandatoryOption(@RequestParam("option.id") Long optionId,
                                        @RequestParam("tariff.id") Long tariffId) {
        Set<OptionDto> optionDtos = new HashSet<>();
        TariffDto tariff = tariffService.getById(tariffId);
        if(!Objects.isNull(tariff.getOptions())) {
             optionDtos = tariff.getOptions();

        }
        optionDtos.add(optionService.getById(optionId));
        tariff.setOptions(optionDtos);
        tariffService.update(tariff);
        return "redirect:/managers/tariffs";
    }
*/
    @RequestMapping(value ="/delete")
    public String deleteTariffById(@RequestParam long id) {
        tariffService.delete(id);
        return "redirect:/managers/tariffs";
    }
}
