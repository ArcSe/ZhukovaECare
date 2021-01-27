package com.javaschool.exception;

import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = Logger.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(ExamplesNotFoundException.class)
    public ModelAndView handleNotFoundExemplarError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex, ex.getCause());
        logger.error(Arrays.stream(ex.getStackTrace()).findFirst());
        //logger.error(Arrays.stream(ex.getStackTrace()).map(s -> s.toString() + "\n").collect(Collectors.toList()));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsp/errors/404notFound");
        return mav;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView handleUserNameNotFoundExemplarError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex, ex.getCause());
        logger.error(Arrays.stream(ex.getStackTrace()).findFirst());
        logger.error(Arrays.stream(ex.getStackTrace()).findFirst());
        //logger.error(Arrays.stream(ex.getStackTrace()).map(s -> s.toString() + "\n").collect(Collectors.toList()));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsp/errors/404notFound");
        return mav;
    }
    @ExceptionHandler(NotDataFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex, ex.getCause());
        logger.error(Arrays.stream(ex.getStackTrace()).findFirst());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsp/errors/404notFound");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleElseExceptionError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex, ex.getCause());
        logger.error(Arrays.stream(ex.getStackTrace()).map(s -> s.toString() + "\n").collect(Collectors.toList()));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsp/errors/500");
        return mav;
    }
}
