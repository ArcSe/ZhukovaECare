package com.javaschool.exceptionHandler;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("jsp/errors/500");
        return mav;
    }
}
