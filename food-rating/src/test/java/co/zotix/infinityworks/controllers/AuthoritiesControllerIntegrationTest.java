/**
 * AuthoritiesControllerTest.java 02/12/2015 21:40 akhettar $$
 * Copyright 2015 zotix.co, L.P. All rights reserved. $$
 */
package co.zotix.infinityworks.controllers;

import co.zotix.infinityworks.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.*;
import org.springframework.http.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;

import static org.junit.Assert.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext

/**
 * AuthoritiesControllerTest: Integration Test
 */
public class AuthoritiesControllerIntegrationTest {

    @Value("${local.server.port}")
    private int port;


    @Test
    public void testSearchAuthorities() {

        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://127.0.0.1:" + port + "/authorities", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("Aberdeen City"));
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("Barnet"));
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("Camden"));
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("Luton"));
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("York"));
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("Glasgow"));

    }

}