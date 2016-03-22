/**
 * AggregatorService.java 28/11/2015 17:51 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.service;

import co.zotix.infinityworks.model.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * AggregatorService class
 */
@Service
public interface AggregatorService {


    /**
     * Calucate the percentage of each rating in given locality
     *
     * @param establishments
     * @return
     */
    List<Rating> aggregate(final Establishments establishments);



}

