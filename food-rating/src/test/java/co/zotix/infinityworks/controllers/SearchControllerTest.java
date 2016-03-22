package co.zotix.infinityworks.controllers;

import co.zotix.infinityworks.*;
import co.zotix.infinityworks.controller.*;
import co.zotix.infinityworks.exception.*;
import co.zotix.infinityworks.model.*;
import co.zotix.infinityworks.service.*;
import co.zotix.infinityworks.util.*;
import org.junit.*;
import org.mockito.*;
import org.springframework.cache.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static co.zotix.infinityworks.Utils.authority;

/**
 * SearchControllerTest.java 03/12/2015 22:08 akhettar $$
 * Copyright 2015 zotix.co, L.P. All rights reserved. $$
 */

public class SearchControllerTest extends ControllerTestFixture {

    @Mock
    private HygieneRatingService service;

    @Mock
    private HygieneRatingAggregatorService aggregator;

    @Mock
    RatingNameResolver resolver;

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private SearchController controller;

    @Mock
    private Cache cache;

    private Authority authority;

    @Mock
    private Authorities authorities;

    @Before
    public void setUP() {

        MockitoAnnotations.initMocks(this);
        super.setUP(controller);
        authority = authority("92", "Barnet", "London");
        setResolverExpectations();

    }

    @Test
    public void testReturnRating() throws Exception {

        Establishments establishments = Utils.establishments();
        List<Rating> ratingList = Utils.rating(establishments, resolver, MAPPING);
        when(service.queryEstablishments(anyString())).thenReturn(establishments);
        setExpectations(establishments, ratingList);
        final MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get("/search?authorityId=92").accept(MediaType.ALL))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Failure, expected 200", 200, status);
        assertEquals("index", mvcResult.getModelAndView().getViewName());
        assertEquals(ratingList, mvcResult.getModelAndView().getModel().get("ratings"));
        assertEquals(authority, mvcResult.getModelAndView().getModel().get("authority"));
    }

    @Test
    public void testExceptionHandled() throws Exception {
        Exception exception = new EstablishmentsNotFoundException("Failed to connect to REST endpoint");
        when(service.queryEstablishments(anyString())).thenThrow(exception);

        final MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get("/search?authorityId=92").accept(MediaType.ALL))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Failure, expected 200", 404, status);
        assertEquals("error", mvcResult.getModelAndView().getViewName());
        assertEquals("http://localhost/search", mvcResult.getModelAndView().getModel().get("url").toString());
        assertEquals(exception, mvcResult.getModelAndView().getModel().get("exception"));

    }

    private void setExpectations(Establishments establishments, List<Rating> ratingList) {
        when(aggregator.aggregate(establishments)).thenReturn(ratingList);
        when(cacheManager.getCache(anyString())).thenReturn(cache);

        when(cacheManager.getCache("authority").get("queryAuthorities", Authorities.class)).thenReturn(authorities);
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(authority);
        when(authorities.getAuthorities()).thenReturn(authorityList);
    }

    private void setResolverExpectations() {
        when(resolver.resolve("5")).thenReturn("5-start");
        when(resolver.resolve("1")).thenReturn("1-start");
        when(resolver.resolve("Exempt")).thenReturn("Exempt");
    }


}