/**
 * SearchControllerTest.java 02/12/2015 21:46 akhettar $$
 * Copyright 2015 co.zotix, L.P. All rights reserved. $$
 */
package co.zotix.infinityworks.controllers;

import co.zotix.infinityworks.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.junit.*;
import org.junit.runner.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.*;
import org.springframework.http.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;

import java.util.*;

import static org.junit.Assert.*;


/**
 * Integration Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class SearchControllerIntegrationTest {

    private static final String NEEDS_IMPROVEMENT = "Needs Improvement";
    private static final String PASS = "Pass";
    private static final String AWAITING_INSPECTION = "Awaiting Inspection";
    @Value("${local.server.port}")
    private int port;

    private static final Logger LOG = LoggerFactory.getLogger(SearchControllerIntegrationTest.class);


    @Test
    public void testSearchEstablishmenItShouldReturnResultForCamdenAuthority() {

        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://127.0.0.1:" + port + "/search?authorityId=93", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Elements results = getResultElements(entity);
        LOG.info(results.text());
        Map expectedEntries = toMap("1-start" + "=>" + "4.94%",
                "Exempt" + "=>" + "3.47%",
                "0-star" + "=>" + "0.93%", "4-star" + "=>" + "23.37%", "3-star" + "=>" + "20.9%", "5-star" + "=>" +
                        "41.91%", "2-star" + "=>" + "4.47%");
        for (Element element : results) {
            assertExpectedResponse(element, expectedEntries);
        }

    }

    @Test
    public void testSearchEstablishmentHygieneProfileForGlasgow() {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://127.0.0.1:" + port + "/search?authorityId=213", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Elements results = getResultElements(entity);
        Map expectedEntries = toMap(NEEDS_IMPROVEMENT + "=>" + "11.16%",
                PASS + "=>" + "76.55%",
                AWAITING_INSPECTION + "=>" + "12.29%");
        for (Element element : results) {
            assertExpectedResponse(element, expectedEntries);
        }

    }

    @Test
    public void testSearchEstablishmentWithDummySearchUri() {

        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://127.0.0.1:" + port + "/search?authority", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());

    }

    private void assertExpectedResponse(Element element, Map<String, String> entries) {
        String status = element.select("td").first().text();
        assertEquals(number(element.select("td").last().text()), number(entries.get(status)));
    }

    private Elements getResultElements(ResponseEntity<String> entity) {
        Document doc = Jsoup.parse(entity.getBody());
        Element resultTable = doc.getElementById("result");
        return resultTable.select("tr.success");
    }

    private int number(String num) {
       return Double.valueOf(num.split("%")[0]).intValue();
    }

    private Map toMap(String... entries) {
        Map<String, String>  collection = new HashMap<>();
        for (String entry : entries) {
            collection.put(entry.split("=>")[0], entry.split("=>")[1]);
        }
        return collection;
    }



}