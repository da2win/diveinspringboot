package com.da2win.diveinspringboot.bootstrap;

import com.da2win.diveinspringboot.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 系统属性条件引导类
 */
@ConditionalOnSystemProperty(name = "username", value = "da2wi")
public class ConditionalOnPropertyBootstrap {

    @Bean
    @ConditionalOnSystemProperty(name = "username", value = "da2win")
    public String helloWorld() {
        return "Hello, World Da2win";
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalOnPropertyBootstrap.class).web(WebApplicationType.NONE).run(args);

        String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println(helloWorld);

        context.close();
    }
}
