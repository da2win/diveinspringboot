package com.da2win.diveinspringboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *  Spring 应用上下文引导类
 */
@SpringBootApplication
public class SpringApplicationContextBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationContextBootstrap.class)
                //.web(WebApplicationType.NONE)
                .run(args);

        System.out.println("ConfigurableApplicationContext 类型: " + context.getClass().getName());
        System.out.println("Environment 类型: " + context.getEnvironment().getClass().getName());

        // close context
        context.close();
    }
}
