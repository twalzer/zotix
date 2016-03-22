/**
 * ControllerTestFixture.java 03/12/2015 22:35 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.controllers;

import co.zotix.infinityworks.controller.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;
import org.springframework.web.method.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.lang.reflect.*;

/**
 * ControllerTestFixture class
 */


@WebAppConfiguration
public abstract class ControllerTestFixture {


    protected MockMvc mock;

    protected static final String MAPPING = "1:1-start,2:2-star,3:3-star,4:4-star,5:5-star,Pass:Pass,Pass and Eat Safe:Pass," +
            "Exempt:Exempt,awaiting,inspection:Awaiting Inspection,Improvement Required:Needs Improvement";

    @Autowired
    protected WebApplicationContext webApplicationContext;



    private ExceptionHandlerExceptionResolver createExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(
                    HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(
                        ErrorHandlingController.class).resolveMethod(exception);
                return new ServletInvocableHandlerMethod(
                        new ErrorHandlingController(), method);
            }
        };
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }
    protected void setUP(final Object controller) {
        mock = MockMvcBuilders.standaloneSetup(controller).setHandlerExceptionResolvers(createExceptionResolver()).build();
    }

    protected void setUP() {
        mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}

