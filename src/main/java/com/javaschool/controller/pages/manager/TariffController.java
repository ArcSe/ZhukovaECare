package com.javaschool.controller.pages.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javaschool.controller.pages.ControllerUtils;
import com.javaschool.dto.ClientDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.exception.RemoveOptionFromMandatorySetException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.jms.JmsProducer;
import com.javaschool.service.OptionService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/managers/tariffs")
public class TariffController {
    private final TariffService tariffService;
    private final OptionService optionService;
    private final JmsProducer producer;

    @Autowired
    public TariffController(TariffService tariffService, OptionService optionService, JmsProducer producer) {
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.producer = producer;
    }
    
    @RequestMapping()
    public ModelAndView home() throws Exception{
        List<TariffDto> listTariff = tariffService.getAll();
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/tariffList");
        mav.addObject("listTariff", listTariff);
        return mav;
    }

    @RequestMapping("/getById")
    public ModelAndView getById(@RequestParam long id) throws Exception {
        TariffDto tariff = tariffService.getById(id);
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/tariffgetById");
        mav.addObject("tariff", tariff);
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateTariff(@Valid @ModelAttribute("tariff") TariffDto tariff,
                               BindingResult bindingResult, Model model) throws Exception {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("tariff", tariff);

            return "jsp/managers/tariffs/edit_tariff";
        }
        TariffDto tariffDB = tariffService.getById(tariff.getId());
        tariffDB.setName(tariff.getName());
        tariffDB.setPrice(tariff.getPrice());
        tariffService.update( tariffDB);
        return "redirect:/managers/tariffs";
    }

    @RequestMapping("/edit")
    public ModelAndView editTariff(@RequestParam long id) throws Exception {
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/edit_tariff");
        TariffDto tariffDto = tariffService.getById(id);
        mav.addObject("tariff", tariffDto);
        return mav;
    }

    @RequestMapping("/new")
    public String newTariff(Map<String, Object> model) throws Exception {
        List<OptionDto> options = optionService.getAll();
        model.put("tariff", new TariffDto());
        model.put("options", options);
        return "jsp/managers/tariffs/new_tariff";
    }

    @RequestMapping(value = "/removeOption", method = RequestMethod.POST)
    public String removeOptionFromTariff(Model model,@RequestParam("optionId") Long optionId,
                                         @RequestParam("tariffId") Long tariffId,
                                         @RequestParam("isFromAddForm") boolean isForm) throws Exception {

        try {
            tariffService.removeOption(optionId, tariffId);
        }
        catch (RemoveOptionFromMandatorySetException e){
            model.addAttribute("tariff", tariffService.getById(tariffId));
            model.addAttribute("removeOptionError", "You can delete this option, because it's mandatory for another options");
            return "jsp/managers/tariffs/tariffgetById";
        }

        if(!isForm) {
            return "redirect:/managers/tariffs/getById?id=" + tariffId;
        }
        else {
            return "redirect:/managers/tariffs/addOption?id=" + tariffId;
        }
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOptionToDB(@RequestParam("optionId") Long optionId,
                                         @RequestParam("tariffId") Long tariffId) throws Exception {
        tariffService.addOption(optionId, tariffId);
        return "redirect:/managers/tariffs/addOption?id="+tariffId;
    }

    @RequestMapping(value = "/addOption")
    public ModelAndView addOptionToTariff(@RequestParam Long id) throws Exception {
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/addOptions");
        TariffDto tariffDto = tariffService.getById(id);
        mav.addObject("tariff", tariffDto);
        if (!tariffDto.getOptions().isEmpty()) {
            Set<OptionDto> tariffOption = tariffDto.getOptions();
            Set<OptionDto> checkedOptions = new HashSet<>();
            tariffOption.forEach(o -> checkedOptions.addAll(optionService.splitSetMandatoryOptionsForTariff(o.getId())));
            checkedOptions.addAll(tariffOption);
            mav.addObject("options", checkedOptions);
        }
        else {
            mav.addObject("options", optionService.getAll());
        }
        return mav;
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveTariff(@Valid @ModelAttribute("tariff") TariffDto tariff,
                             BindingResult bindingResult, Model model) throws Exception {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("tariff", tariff);

            return "jsp/managers/tariffs/new_tariff";
        }
        if(!tariffService.add(tariff)){
            model.addAttribute("nameError", "Tariff already exist");
            return "jsp/managers/tariffs/new_tariff";
        }
        tariffService.add(tariff);
        return "redirect:/managers/tariffs";
    }
/*
    @RequestMapping("/editOptions")
    public ModelAndView editMandatoryOptions(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/managers/tariffs/addOptions");
        mav.addObject("tariff", tariffService.getById(id));
        mav.addObject("options", optionService.getAll());
        return mav;
    }
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
    @RequestMapping(value ="/delete", method = RequestMethod.POST)
    public String deleteTariffById(@RequestParam("tariffId") long id) throws Exception {
        tariffService.delete(id);
        return "redirect:/managers/tariffs";
    }

    @RequestMapping(value ="/NewTariffs", method = RequestMethod.GET)
    public String sendNewTariff() throws Exception {
        producer.send("push");
        return "redirect:/managers/tariffs";
    }
}
