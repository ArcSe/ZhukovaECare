package com.javaschool.controller;

import com.javaschool.model.Option;
import com.javaschool.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class OptionController {

    private OptionRepository optionRepository;

    @Autowired
    public OptionController(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        Iterable<Option> listOption = optionRepository.findAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listOption", listOption);
        return mav;
    }

    @RequestMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Option option = new Option();
        model.put("option", option);
        return "new_option";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("option") Option option) {
        optionRepository.save(option);
        return "redirect:/";
    }

}
