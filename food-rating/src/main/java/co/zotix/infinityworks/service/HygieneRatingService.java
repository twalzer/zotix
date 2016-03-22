/**
 * FoodRatingService.java 25/11/2015 22:10 akhettar $$
 * Copyright 2015 Zotix.co, L.P. All rights reserved. $$
 */
package co.zotix.infinityworks.service;

import co.zotix.infinityworks.model.Authorities;
import co.zotix.infinityworks.model.Establishments;

/**
 * FoodRatingService class
 */
public interface HygieneRatingService {

    /**
     * Queries all the authorities in the UK.
     *
     * @return
     */
    Authorities queryAuthorities() throws Exception;

    /**
     * Get all the establishment for given authority
     *
     * @return
     */
    Establishments queryEstablishments(final String id) throws Exception;

}
