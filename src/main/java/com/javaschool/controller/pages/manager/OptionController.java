package com.javaschool.controller.pages.manager;

import com.javaschool.dto.OptionDto;
import com.javaschool.dto.UserDto;
import com.javaschool.model.Role;
import com.javaschool.model.User;
import com.javaschool.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/managers/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping()
    public ModelAndView home(@AuthenticationPrincipal User user) {
        List<OptionDto> listOption = optionService.getAll();
        ModelAndView mav = new ModelAndView("jsp/options/home");
        mav.addObject("listOption", listOption);
        return mav;
    }
    @RequestMapping("/new")
    public String newOption(Map<String, Object> model) {
        OptionDto option = new OptionDto();
        model.put("option", option);
        return "jsp/options/new_option";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOption(@ModelAttribute("option") OptionDto option) {
        optionService.add(option);
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateOption(@ModelAttribute("option") OptionDto option) {
        optionService.update(option);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public ModelAndView editOption(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/options/edit_option");
        OptionDto option = optionService.getById(id);
        mav.addObject("option", option);
        return mav;
    }

    @RequestMapping("/editMandatoryOptions")
    public ModelAndView editMandatoryOptions(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("jsp/options/addMandatoryOptions");
        mav.addObject("option", optionService.getById(id));
        mav.addObject("options", optionService.getAll());
        return mav;
    }

    @RequestMapping(value = "/updateMandatoryOptions", method = RequestMethod.POST)
    public String updateMandatoryOption(@ModelAttribute("option") OptionDto option) {
        optionService.update(option);
        return "redirect:/managers/options";
    }

    @RequestMapping(value = "/delete")
    public String deleteOptionById(@RequestParam long id) {
        optionService.delete(id);
        return "redirect:/";
    }

}
