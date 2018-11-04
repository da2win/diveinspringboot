package com.da2win.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HelloWorld {@link Controller}
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String index(@RequestParam int value, Model model) {
        //model.addAttribute("acceptLanguage", acceptLanguage);
        //model.addAttribute("jsessionId", jsessionId);
        //model.addAttribute("message", "Hello, World");
        return "index";
    }

}
