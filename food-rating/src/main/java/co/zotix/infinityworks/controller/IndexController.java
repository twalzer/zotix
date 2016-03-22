/**
 * IndexController.java 30/11/2015 07:34 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 * IndexController class
 */
@Controller
public class IndexController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String authorities(Model model) {
        return "redirect:authorities";
    }
}

