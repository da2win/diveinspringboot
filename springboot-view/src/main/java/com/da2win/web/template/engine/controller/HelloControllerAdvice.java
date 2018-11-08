package com.da2win.web.template.engine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * {@link HelloWorldController} 通知
 *
 *
 */
@ControllerAdvice(assignableTypes = HelloWorldController.class)
public class HelloControllerAdvice {

    @ModelAttribute("acceptLanguage")
    public String acceptLanguage(@RequestHeader("Accept-Language") String acceptLanguage) {
        return acceptLanguage;
    }

    @ModelAttribute("jsessionId")
    public String jsessionId(@CookieValue(value="JSESSIONID", required = false) String jsessionId) {
        return jsessionId;
    }

    @ModelAttribute("message")
    public String message() {
        return "Hello, World";
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> onException(Throwable throwable) {
        return ResponseEntity.ok(throwable.getMessage());
    }
}