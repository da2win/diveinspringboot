package com.da2win.spring.boot.web.servlet;

import com.da2win.spring.web.servlet.AsyncServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Spring boot 引导类
 */
@EnableAutoConfiguration
//@ServletComponentScan("com.da2win.spring.web.servlet")
public class SpringBootServletBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletBootstrap.class, args);
    }

    @Bean
    public ServletRegistrationBean asyncServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new AsyncServlet());
        registrationBean.addUrlMappings("/async-servlet");
        return registrationBean;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            FilterRegistration.Dynamic registration = servletContext.addFilter("filter", filter);
            registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");

        };
    }
}
