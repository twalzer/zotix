package co.zotix.infinityworks.services;

import co.zotix.infinityworks.*;
import co.zotix.infinityworks.model.*;
import co.zotix.infinityworks.service.*;
import co.zotix.infinityworks.util.*;
import org.junit.*;
import org.mockito.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * HygieneRatingAggregatorServiceTest.java 02/12/2015 22:19 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */
public class HygieneRatingAggregatorServiceTest {

    private static final String MAPPING = "1:1-start,2:2-star,3:3-star,4:4-star,5:5-star,0:0-star,Pass:Pass,Pass " +
            "and Eat Safe:Pass,Exempt:Exempt,Awaiting Inspection:Awaiting Inspection,Improvement Required:Needs Improvement";
    @Mock
    private RatingNameResolver resolver;
    @Mock
    private Establishments establishments = new Establishments();
    private HygieneRatingAggregatorService service;

    @Before
    public void setUP() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new HygieneRatingAggregatorService();
        service.setRatingNameResolver(resolver);
        service.setRatingMapping(MAPPING);
        when(resolver.resolve("5")).thenReturn("5-star");
        when(resolver.resolve("1")).thenReturn("1-star");
        when(resolver.resolve("Exempt")).thenReturn("Exempt");

    }


    @Test
    public void testWithListOfEstablishmentShouldReturnPercentageForEachRating() {

        Establishments expected = Utils.establishments();
        when(establishments.getEstablishments()).thenReturn(expected.getEstablishments());
        List<Rating> ratings = service.aggregate(establishments);
        assertEquals("50.0%", getValue(ratings, "5-star"));
        assertEquals("25.0%", getValue(ratings, "1-star"));
        assertEquals("25.0%", getValue(ratings, "Exempt"));
    }

    @Test
    public void testWithAnEmptyListOfEstablishementsRatingListShouldBeZero() {

        when(establishments.getEstablishments()).thenReturn(Collections.EMPTY_LIST);
        List<Rating> ratings = service.aggregate(establishments);
        assertTrue(ratings.size() == 0);

    }

    private String getValue(final List<Rating> ratings, final String ratingName) {

        for (Rating rating : ratings) {
            if (rating.getRating().equals(ratingName)) {
                return rating.toString();
            }
        }
        return null;
    }

}