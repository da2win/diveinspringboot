package com.da2win.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * {@link Properties}
 * @Author Darwin
 * @Date 2018/11/14 9:03
 */
@RestController
public class PropertiesRestController {


    @PostMapping(value = "/add/props",
            consumes = "text/properties;charset=utf-8" // Content-Type 过滤媒体类型
    )
    public Properties addProperties(
            //@RequestBody
                    Properties properties) {
        return properties;
    }
}
