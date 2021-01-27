package com.javaschool.exception.notFound;

import com.javaschool.controller.pages.anon.AnonPageController;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = Logger.getLogger(AnonPageController.class);

    @ExceptionHandler(TariffNotFoundException.class)
    public ModelAndView handleNotFoundExemplarError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex, ex.getCause());
        //logger.error(Arrays.stream(ex.getStackTrace()).map(s -> s.toString() + "\n").collect(Collectors.toList()));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsp/errors/404notFound");
        return mav;
    }

    @ExceptionHandler(NotDataFoundException.class)
    public ModelAndView handleNotDataFoundError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex, ex.getCause());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsp/errors/404notFound");
        return mav;
    }
}
