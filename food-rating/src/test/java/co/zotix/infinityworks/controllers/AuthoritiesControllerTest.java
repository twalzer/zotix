/**
 * AuthoritiesControllerTest.java 05/12/2015 23:11 akhettar $$
 * Copyright 2015 co.zotix, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.controllers;

import co.zotix.infinityworks.controller.*;
import co.zotix.infinityworks.exception.*;
import co.zotix.infinityworks.service.*;
import org.junit.*;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static co.zotix.infinityworks.Utils.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * AuthoritiesControllerTest: Unit Test
 */
public class AuthoritiesControllerTest extends ControllerTestFixture {

    @Mock
    private HygieneRatingService service;

    @InjectMocks
    private AuthoritiesController controller;

    @Before
    public void setUP() {

        MockitoAnnotations.initMocks(this);
        super.setUP(controller);

    }

    @Test
    public void testSearchAuthoritiesShouldReturnListOfAuthorities() throws Exception {
        when(service.queryAuthorities()).thenReturn(authorities());

        final MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get("/authorities").accept(MediaType.ALL))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Failure, expected 200", 200, status);
        assertEquals("index",mvcResult.getModelAndView().getViewName());
        assertEquals(authorities().getAuthorities(),mvcResult.getModelAndView().getModel().get("authorities"));
    }

    @Test
    public void testSearchAuthoritiesNoneFoundShouldThrowAuthoritiesNotFoundException() throws Exception {

        Exception exception = new AuthorityNotFoundException("Failed to connect to REST endpoint");
        when(service.queryAuthorities()).thenThrow(exception);

        final MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get("/authorities").accept(MediaType.ALL))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Failure, expected 200", 404, status);
        assertEquals("error", mvcResult.getModelAndView().getViewName());
        assertEquals("http://localhost/authorities", mvcResult.getModelAndView().getModel().get("url").toString());
        assertEquals(exception, mvcResult.getModelAndView().getModel().get("exception"));
    }
}

