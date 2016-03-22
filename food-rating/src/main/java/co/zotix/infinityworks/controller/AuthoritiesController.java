/**
 * IndexController.java 22/11/2015 11:49 akhettar $$
 * Copyright 2015 zotix.co, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.controller;

import co.zotix.infinityworks.model.Authority;
import co.zotix.infinityworks.service.HygieneRatingService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IndexController class
 * @author akhettar
 */
@Controller
public class AuthoritiesController {


    @Autowired
    private HygieneRatingService service;

    @RequestMapping(value = "/authorities", method = RequestMethod.GET)
    public String authorities(Model model) throws Exception {
        List<Authority> authorities = service.queryAuthorities().getAuthorities();
        model.addAttribute("authorities", authorities);
        model.addAttribute("url", "/search?authorityId=");
        return "index";
    }


}

