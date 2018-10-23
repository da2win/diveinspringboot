package com.da2win.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloWorld {@link Controller}
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
