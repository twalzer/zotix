/**
 * Utils.java 28/11/2015 21:25 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.util;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Utils class
 */
@Service
public class RatingNameResolver {

    private static final String COMMA = ",";
    private Map<String, String> cache;
    private Logger LOG = LoggerFactory.getLogger(RatingNameResolver.class);

    @Value("${rating.display.mapping}")
    private String mappings;

    /**
     * Gets the display name in the view for given rating
     *
     * @param given
     * @return
     */
    public String resolve(final String given) {
        if (cache == null) {
            load();
        }
        if (cache.get(given) == null) {
            LOG.warn("Unknown rating encountered {}", given);
        }
        return cache.get(given) == null ? given : cache.get(given);
    }

    private void load() {
        cache = new HashMap<>();
        String[] ratings = mappings.split(COMMA);
        for (String rating : ratings) {
            String[] values = rating.split(":");
            cache.put(values[0], values[1]);
        }
    }

    public void setMappings(final String mappings) {
        this.mappings = mappings;
    }

}

