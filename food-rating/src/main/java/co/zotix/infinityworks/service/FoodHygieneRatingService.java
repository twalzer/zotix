/**
 * FoodRatingServiceImp.java 25/11/2015 22:08 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */
package co.zotix.infinityworks.service;

import co.zotix.infinityworks.exception.*;
import co.zotix.infinityworks.model.Authorities;
import co.zotix.infinityworks.model.Establishments;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * FoodRatingServiceImp class
 */

@Service
public class FoodHygieneRatingService implements HygieneRatingService {

    @Autowired
    private RestTemplate template;


    @Value("${authorities.endpoint}")
    private String authorityEndpoint;

    @Value("${establishment.endpoint}")
    private String establishmentEndpoint;


    @Cacheable(value = "authority", key="#root.method.name")
    @Override
    public Authorities queryAuthorities() throws Exception {
        HttpHeaders headers = headers();
        ResponseEntity<Authorities> response = template.exchange(authorityEndpoint,
                HttpMethod.GET,
                new HttpEntity<byte[]>(headers),
                Authorities.class);
        Authorities result = response.getBody();
        checkStatus(response);
        if (result.getAuthorities().size() == 0) {
            throw new AuthorityNotFoundException("No Authorities found");
        }
        return result;
    }

    @Cacheable(value = "establishment")
    @Override
    public Establishments queryEstablishments(final String authorityId) throws Exception {
        String uriWithParam = addQueryParam(establishmentEndpoint, authorityId);
        HttpHeaders headers = headers();
        ResponseEntity<Establishments> response = template.exchange(uriWithParam,
                HttpMethod.GET,
                new HttpEntity<byte[]>(headers),
                Establishments.class);
        Establishments result = response.getBody();
        checkStatus(response);
        if (result.getEstablishments().size() == 0) {
            throw new EstablishmentsNotFoundException("No Establishment found for given authorityId: " + authorityId);
        }
        return result;
    }

    private void checkStatus(ResponseEntity response) throws Exception {
        if (response.getStatusCode() != HttpStatus.OK)
        {
            throw new Exception("Internal Server Error");
        }
    }



    /**
     * Set the headers
     *
     * @return
     */
    private static HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-version", String.valueOf(2));
        headers.add("accept", "application/json");
        return headers;
    }


    private String addQueryParam(final String endpoint, final String authorityId) {
        StringBuilder builder = new StringBuilder();
        builder.append(endpoint);
        builder.append("?");
        builder.append("localAuthorityId=");
        builder.append(authorityId);
        return builder.toString();
    }


}
