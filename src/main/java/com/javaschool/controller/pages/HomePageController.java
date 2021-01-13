package com.javaschool.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @RequestMapping(value = "/")
    public String getHomePage(){
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
