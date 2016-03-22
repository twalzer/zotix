/**
 * IndexControllerTest.java 06/12/2015 09:27 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.controllers;

import co.zotix.infinityworks.controller.*;
import org.junit.*;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static co.zotix.infinityworks.Utils.*;
import static org.junit.Assert.*;

/**
 * IndexControllerTest class
 */
public class IndexControllerTest extends ControllerTestFixture {

    @InjectMocks
    IndexController controller;

    @Before
    public void setUP() {

        MockitoAnnotations.initMocks(this);
        super.setUP(controller);
    }

    @Test
    public void testSearchAuthoritiesShouldReturnListOfAuthorities() throws Exception {

        final MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get("/").accept(MediaType.ALL))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Failure, expected 302", HttpStatus.FOUND.value(), status);
        assertEquals("redirect:authorities", mvcResult.getModelAndView().getViewName());
    }
}

