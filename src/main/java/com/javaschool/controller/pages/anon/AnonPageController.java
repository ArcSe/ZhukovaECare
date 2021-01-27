package com.javaschool.controller.pages.anon;

import com.javaschool.dto.TariffDto;
import com.javaschool.model.User;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AnonPageController {

    private final TariffService tariffService;

    @Autowired
    public AnonPageController(TariffService tariffService) {
        this.tariffService = tariffService;
    }


    @RequestMapping(value = "/")
    public String getHomePage(){
        /*
        List<TariffDto> listTariff = tariffService.getAll();
        ModelAndView mav = new ModelAndView("jsp/client/index");
        mav.addObject("tariffs", listTariff);
        return mav;
         */
        return "jsp/index";
    }



    @RequestMapping(value = "/404")
    public String get404(){
        return "jsp/errors/404notFound";
    }

    @RequestMapping(value = "/500")
    public String get500(){
        return "jsp/errors/500";
    }

    @RequestMapping(value = "/403")
    public String get403(){
        return "jsp/errors/403";
    }
}
