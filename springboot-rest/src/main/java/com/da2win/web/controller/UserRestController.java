package com.da2win.web.controller;

import com.da2win.web.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Darwin
 * @Date 2018/11/14 9:03
 */
@RestController
public class UserRestController {


    @PostMapping(value = "/user",
            consumes = "application/json;charset=UTF-8",
            produces= "application/json;charset=gbk")
    public User user(@RequestBody User user) {
        return user;
    }
}
