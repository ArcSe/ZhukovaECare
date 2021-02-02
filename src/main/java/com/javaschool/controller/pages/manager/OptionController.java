package com.javaschool.controller.pages.manager;

import com.javaschool.controller.pages.ControllerUtils;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.UserDto;
import com.javaschool.model.Option;
import com.javaschool.model.Role;
import com.javaschool.model.User;
import com.javaschool.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/managers/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping()
    public ModelAndView home() throws Exception{
        List<OptionDto> listOption = optionService.getAll();
        ModelAndView mav = new ModelAndView("jsp/managers/options/home");
        mav.addObject("listOption", listOption);
        return mav;
    }

    @RequestMapping("/new")
    public String newOption(Map<String, Object> model) throws Exception{
        OptionDto option = new OptionDto();
        model.put("option", option);
        return "jsp/managers/options/new_option";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveOption( @Valid @ModelAttribute("option") OptionDto option,
                             BindingResult bindingResult, Model model) throws Exception{
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("option", option);
            System.out.println(errorsMap);

            return "jsp/managers/options/new_option";
        }

            optionService.add(option);
            return "redirect:/managers/options";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateOption(@Valid @ModelAttribute("option") OptionDto option,
                               BindingResult bindingResult, Model model) throws Exception{
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("option", option);
            System.out.println(errorsMap);

            return "jsp/managers/options/new_option";
        }
        optionService.update(option);
        return "redirect:/managers/options";
    }

    @RequestMapping("/edit")
    public ModelAndView editOption(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/options/edit_option");
        OptionDto option = optionService.getById(id);
        mav.addObject("option", option);
        return mav;
    }

    @RequestMapping("/editMandatoryOptions")
    public ModelAndView editMandatoryOptions(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/options/addMandatoryOptions");
        OptionDto optionDB = optionService.getById(id);
        mav.addObject("option", optionDB);
        mav.addObject("options", optionService.splitSetMandatoryOptions(id));
        return mav;
    }
/*
    @RequestMapping(value = "/updateMandatoryOptions", method = RequestMethod.POST)
    public String updateMandatoryOption(@ModelAttribute("option") OptionDto option) {
        optionService.update(option);
        return "redirect:/managers/options";
    }

 */

    @RequestMapping(value = "/delete")
    public String deleteOptionById(@RequestParam long id) throws Exception{
        optionService.delete(id);
        return "redirect:/managers/options";
    }

    @PostMapping("/addMandatoryOption")
    private String addMandatoryOption(@RequestParam("optionId") long optionId,
                             @RequestParam("mandatoryOptionId") long mandatoryOptionId) throws Exception{
        optionService.addMandatory(optionId, mandatoryOptionId);
        editMandatoryOptions(optionId);
        return "redirect:/managers/options/editMandatoryOptions?id="+ optionId;
    }

    @PostMapping("/deleteMandatoryOption")
    public String  deleteMandatoryOption(@RequestParam("optionId") Long optionId,
                              @RequestParam("mandatoryOptionId") Long optionMandatoryId,
                              Model model) throws Exception{
        optionService.deleteMandatoryOption(optionId, optionMandatoryId);
        return "redirect:/managers/options/editMandatoryOptions?id="+ optionId;
    }

    @RequestMapping("/editBannedOptions")
    public ModelAndView editBannedOptions(@RequestParam long id) throws Exception{
        ModelAndView mav = new ModelAndView("jsp/managers/options/addBannedOptions");
        Set<OptionDto> options = optionService.getAll().stream().filter(o->o.getId()!=id).collect(Collectors.toSet());
        mav.addObject("option", optionService.getById(id));
        mav.addObject("options", optionService.splitSetBannedOptions(id));
        return mav;
    }

    @PostMapping("/addBannedOption")
    private String addBannedOption(@RequestParam("optionId") long optionId,
                             @RequestParam("bannedOptionId") long bannedOptionId) throws Exception {
        optionService.addBannedOptionToDB(optionId, bannedOptionId);
        editBannedOptions(optionId);
        return "redirect:/managers/options/editBannedOptions?id="+ optionId;
    }

    @PostMapping("/deleteBannedOption")
    public String  deleteBannedOption(@RequestParam("optionId") Long optionId,
                                         @RequestParam("bannedOptionId") Long optionBannedId,
                                         Model model) throws Exception{
        optionService.deleteBannedOption(optionId, optionBannedId);
        return "redirect:/managers/options/editBannedOptions?id="+ optionId;
    }
}
