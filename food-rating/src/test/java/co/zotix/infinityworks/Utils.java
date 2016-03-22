/**
 * Utils.java 03/12/2015 22:46 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks;

import co.zotix.infinityworks.model.*;
import co.zotix.infinityworks.service.*;
import co.zotix.infinityworks.util.*;

import java.util.*;

/**
 * Utils class
 */
public final class Utils {

    /**
     * Build Establishment List
     */
    public static Establishments establishments() {
        Establishment est1 = new Establishment();
        Establishment est2 = new Establishment();
        Establishment est3 = new Establishment();
        Establishment est4 = new Establishment();
        est1.setRatingValue("5");
        est2.setRatingValue("5");
        est3.setRatingValue("1");
        est4.setRatingValue("Exempt");
        List<Establishment> establishmentList = new ArrayList<>();
        establishmentList.add(est1);
        establishmentList.add(est2);
        establishmentList.add(est3);
        establishmentList.add(est4);
        Establishments establishments = new Establishments();
        establishments.setEstablishments(establishmentList);
        return establishments;
    }

    public static List<Rating> rating(final Establishments establishments, final RatingNameResolver resolver, final String mappings) {
        HygieneRatingAggregatorService service = new HygieneRatingAggregatorService();
        service.setRatingMapping(mappings);
        service.setRatingNameResolver(resolver);
        return service.aggregate(establishments);
    }

    public static Authority authority(final String id, final  String name, final String regionName ) {

        Authority authority = new Authority();
        authority.setLocalAuthorityId(id);
        authority.setName(name);
        authority.setRegionName(regionName);
        return authority;
    }

    public static Authorities authorities() {
        Authorities authorities = new Authorities();
        Authority auth1 = new Authority();
        auth1.setLocalAuthorityId("92");
        auth1.setRegionName("London");
        auth1.setName("Camden");
        Authority auth2 = new Authority();
        auth2.setLocalAuthorityId("93");
        auth2.setName("Ashford");
        auth2.setRegionName("Kent");
        List<Authority> list = new ArrayList<>();
        list.add(auth1);
        list.add(auth2);
        authorities.setAuthorities(list);
        return authorities;

    }
}

