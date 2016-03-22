package co.zotix.infinityworks.services;

import co.zotix.infinityworks.model.*;
import co.zotix.infinityworks.service.*;
import co.zotix.infinityworks.util.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * EnglandAggregatorServiceTest.java 29/11/2015 11:23 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */
public class RatingAggregatorServiceTest {

    private HygieneRatingAggregatorService service;
    private RatingNameResolver resolver;
    private static final String MAPPING = "1:1-start,2:2-star,3:3-star,4:4-star,5:5-star,Pass:Pass,Pass and Eat Safe:Pass,Exempt:Exempt," +
            "awaiting " +
            "inspection:Awaiting Inspection,Improvement Required:Needs Improvement";

    @Before
    public void setUP() {
        resolver = new RatingNameResolver();
        resolver.setMappings(MAPPING);
        service = new HygieneRatingAggregatorService();
        service.setRatingNameResolver(resolver);
        service.setRatingMapping(MAPPING);
    }

    @Test
    public void testReturnRatingSuccessEngland() {

        Establishments establishments = buildEstablishment("1","1","2","5","5","Exempt");
        final List<Rating> ratings = service.aggregate(establishments);
        assertTrue(ratings.size() > 0);
        assertEquals(33.33, getValue(ratings, resolver.resolve(RatingValue.ONE_STAR)), 0);
        assertEquals(0, getValue(ratings, resolver.resolve(RatingValue.THREE_STAR)), 0);
        assertEquals(16.67, getValue(ratings, resolver.resolve(RatingValue.TWO_STAR)), 0);
        assertEquals(0, getValue(ratings, resolver.resolve(RatingValue.FOUR_STAR)), 0);
        assertEquals(33.33, getValue(ratings, resolver.resolve(RatingValue.FIVE_STAR)), 0);
        assertEquals(16.67, getValue(ratings, resolver.resolve(RatingValue.EXEMPT)), 0);
        assertEquals(100, total(ratings));
    }

    @Test
    public void testReturnRatingSuccessScotland() {

        Establishments establishments = buildEstablishment("Improvement Required","Pass","Improvement Required","Pass and Eat Safe","Pass");
        final List<Rating> ratings = service.aggregate(establishments);
        assertTrue(ratings.size() > 0);
        assertEquals(60, getValue(ratings, resolver.resolve(RatingValue.PASS)), 0);
        assertEquals(40, getValue(ratings, resolver.resolve(RatingValue.IMPROVEMENT_REQUIRED)), 0);
        assertEquals(100, total(ratings));
    }

    protected double getValue(List<Rating> ratings, String name) {
        for (Rating rating : ratings) {
            if (rating.getRating().equals(name)) {
                return rating.getPercentage();
            }
        }
        return 0;
    }

    protected Establishments buildEstablishment(final String... ratings) {

        Establishments establishments = new Establishments();
        List<Establishment> values = new ArrayList<>();
        for (String rating : ratings) {
            Establishment est = new Establishment();
            est.setRatingValue(rating);
            values.add(est);

        }
        establishments.setEstablishments(values);
        return establishments;
    }

    protected int total(List<Rating> ratings) {
        double total = 0;
        for (Rating rating : ratings){
            total+=rating.getPercentage();
        }
        return (int)total;
    }




}