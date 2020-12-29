package com.javaschool.controller;

import com.javaschool.model.Option;
import com.javaschool.repository.OptionRepository;
import com.javaschool.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class OptionController {

    private OptionsService optionService;

    @Autowired
    public OptionController(OptionsService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        Iterable<Option> listOption = optionService.findAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listOption", listOption);
        return mav;
    }

    @RequestMapping("/new")
    public String newOption(Map<String, Object> model) {
        Option option = new Option();
        model.put("option", option);
        return "options/new_option";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOption(@ModelAttribute("option") Option option) {
        optionService.save(option);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public ModelAndView editOption(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("options/edit_option");
        Option option = optionService.get(id);
        mav.addObject("option", option);

        return mav;
    }



    @RequestMapping("/delete")
    public String deleteOptionById(@RequestParam long id) {
        optionService.delete(id);
        return "redirect:/";
    }

}
