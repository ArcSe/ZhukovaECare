package com.javaschool.controller.pages.anon;

import com.javaschool.service.TariffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AnonPageController {

    private final TariffService tariffService;
    private static final Logger logger = Logger.getLogger(AnonPageController.class);

    @Autowired
    public AnonPageController(TariffService tariffService) {
        this.tariffService = tariffService;
    }


    @RequestMapping(value = "/")
    public String getHomePage(){
        //logs debug message
        if(logger.isDebugEnabled()){
            logger.debug("getWelcome is executed!");
        }

        //logs exception
        logger.error("This is Error message", new Exception("Testing"));
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
