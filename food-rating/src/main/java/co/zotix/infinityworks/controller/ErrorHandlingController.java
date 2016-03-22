/**
 * ExceptionHandlingController.java akhettar $$
 * Copyright 2015 zotix.co. All rights reserved. $$
 */

package co.zotix.infinityworks.controller;

import co.zotix.infinityworks.exception.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;

/**
 * ExceptionHandlingController class
 */
@ControllerAdvice
public class ErrorHandlingController {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlingController.class);

    @ExceptionHandler(EstablishmentsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleEstablishmentFoundException(final EstablishmentsNotFoundException ex, final HttpServletRequest req) {
        LOG.debug("handling 404 error on a todo entry");
        return getModelAndView(ex, req);
    }

    @ExceptionHandler(AuthorityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleAuthorityFoundException(final AuthorityNotFoundException ex, final HttpServletRequest req) {
        LOG.debug("handling 404 error on a todo entry");
        return getModelAndView(ex, req);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleDefaultError(final EstablishmentsNotFoundException ex, final HttpServletRequest req) {
        LOG.debug("Internal Server Error");
        return getModelAndView(ex, req);
    }


    private ModelAndView getModelAndView(final RuntimeException ex, final HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}

