package com.javaschool.controller;

import com.javaschool.dto.OptionDto;
import com.javaschool.model.Option;
import com.javaschool.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class OptionController {

    private OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        List<OptionDto> listOption = optionService.getAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listOption", listOption);
        return mav;
    }

    @RequestMapping("options/new")
    public String newOption(Map<String, Object> model) {
        Option option = new Option();
        model.put("option", option);
        return "options/new_option";
    }

    @RequestMapping(value = "options/save", method = RequestMethod.POST)
    public String saveOption(@ModelAttribute("option") Option option) {
        optionService.add(option);
        return "redirect:/";
    }
    @RequestMapping(value = "options/update", method = RequestMethod.POST)
    public String updateOption(@ModelAttribute("option") Option option) {
        optionService.update(option);
        return "redirect:/";
    }

    @RequestMapping("options/edit")
    public ModelAndView editOption(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("options/edit_option");
        Option option = optionService.getById(id);
        mav.addObject("option", option);
        return mav;
    }



    @RequestMapping("options/delete")
    public String deleteOptionById(@RequestParam long id) {
        optionService.delete(id);
        return "redirect:/";
    }

}
