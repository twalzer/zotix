/**
 * EstablishementController.java 23/11/2015 21:55 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.controller;

import co.zotix.infinityworks.model.*;
import co.zotix.infinityworks.service.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * EstablishementController class
 */
@Controller
public class SearchController {

    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);
    private static final String SEARCH_URL = "/search?authorityId=";

    @Autowired
    private HygieneRatingService service;

    @Autowired
    private AggregatorService aggregator;

    @Autowired
    private CacheManager cacheManager;


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String establishments(@RequestParam(value = "authorityId", required = true) String authorityId, Model model) throws Exception {

        LOG.info("Querying establishments for given authorityId: {}", authorityId);

        Establishments establishments = service.queryEstablishments(authorityId);
        LOG.info("Found {} establishments", establishments.getEstablishments().size());

        List<Rating> ratings = aggregator.aggregate(establishments);
        model.addAttribute("ratings", ratings);
        model.addAttribute("authorities", authorities() != null ? authorities().getAuthorities() : null);
        model.addAttribute("authority", authority(authorityId));
        model.addAttribute("url", SEARCH_URL);
        return "index";
    }

    /**
     * Load authorities from the cache.
     *
     * @return
     */
    private Authorities authorities() {
        return cacheManager.getCache("authority").get("queryAuthorities", Authorities.class);
    }

    /**
     * Get Authority for given Id from the cache
     *
     * @param authorityId
     * @return
     */
    private Authority authority(final String authorityId) {
        Authorities authorities = cacheManager.getCache("authority").get("queryAuthorities", Authorities.class);
        if (authorities != null) {
            for (Authority authority : authorities.getAuthorities()) {
                if (authority.getLocalAuthorityId().equals(authorityId)) {
                    return authority;
                }
            }
        }
        return null;
    }
}

