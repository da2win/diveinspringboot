package com.da2win.diveinspringboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() { // 方法名即Bean名称
        return "Hello, World 2018";

    }
}
