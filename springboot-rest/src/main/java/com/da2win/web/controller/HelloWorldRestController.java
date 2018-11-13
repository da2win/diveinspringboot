package com.da2win.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorld {@link RestController} 实现
 * @Author Darwin
 * @Date 2018/11/13 15:56
 */
@RestController
public class HelloWorldRestController {


    @GetMapping(value ="/hello-world")
    public String helloWorld(@RequestParam(required = false) String message) {
        return "Hello, World" + message;
    }

}
