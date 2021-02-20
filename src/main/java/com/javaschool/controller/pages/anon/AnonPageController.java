package com.javaschool.controller.pages.anon;

import com.javaschool.service.TariffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AnonPageController {


    public AnonPageController() {

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
