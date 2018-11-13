package com.da2win.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Rest 引导类
 *
 * @Author Darwin
 * @Date 2018/11/13 15:55
 */
@SpringBootApplication(scanBasePackages = "com.da2win.web")
public class SpringBootTestBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestBootstrap.class, args);
    }
}
