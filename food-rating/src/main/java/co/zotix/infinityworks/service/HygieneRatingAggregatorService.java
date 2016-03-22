/**
 * RatingAggregatorService.java 29/11/2015 20:29 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.service;

import co.zotix.infinityworks.model.*;
import co.zotix.infinityworks.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;
import java.util.concurrent.atomic.*;

/**
 * RatingAggregatorService class
 */
@Service
public class HygieneRatingAggregatorService implements AggregatorService {

    @Value("${rating.display.mapping}")
    private String ratingMapping;

    @Value("${exclude.unknown.rating}")
    boolean excludeUnknownRating;

    @Autowired
    private RatingNameResolver resolver;

    private Logger LOG = LoggerFactory.getLogger(HygieneRatingAggregatorService.class);


    /**
     * aggregate the ratings for given establishments
     *
     * @param establishments
     * @return
     */
    @Cacheable(value = "aggregator")
    public List<Rating> aggregate(final Establishments establishments) {

        Map<String, AtomicInteger> ratings = new HashMap<>();

        // using the latest collection enhancement which comes with Java 8
        List<Establishment> establishmentList = establishments.getEstablishments();
        establishmentList.forEach((establishment) -> ratings.compute(resolver.resolve(establishment.getRatingValue()),
                (k,v) -> v == null ? new AtomicInteger(1) : new AtomicInteger(v.incrementAndGet())));

        // work out the percentage
        List<Rating> computed = new ArrayList<>();
        AtomicInteger numberOfEstablishments = new AtomicInteger(establishmentList.size());

        ratings.forEach((k,v) -> {
            if (unknownRating(k) && excludeUnknownRating) {
                numberOfEstablishments.decrementAndGet();
                LOG.warn("Unknown raging encountered");
            } else {
                computed.add(rating(v.intValue(), numberOfEstablishments.intValue(), k));
            }
        });
        return computed;

    }

    private boolean unknownRating(String rating) {
        return !ratingMapping.toLowerCase().contains(rating.toLowerCase());
    }

    private double percentage(int rating, int numberOfEstablishments) {
        return new BigDecimal((((float)rating/numberOfEstablishments)*100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    protected Rating rating(int oneStars, int numberOfEstablishments, String rating) {
        return new Rating(rating, percentage(oneStars, numberOfEstablishments));
    }

    public void setRatingMapping(final String mapping) {
        this.ratingMapping = mapping;
    }

    public void setRatingNameResolver(final RatingNameResolver resolver) {
        this.resolver = resolver;
    }

}

