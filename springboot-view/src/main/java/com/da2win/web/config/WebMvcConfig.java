package com.da2win.web.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Spring Web MVC 配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Bean
    public ViewResolver myViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        // Thymeleaf Ordered.LOWEST_PRECEDENCE - 5
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        // 优先级高于 ThymeleafResolver
        // 配置 ViewResolver 的 Content-Type
        viewResolver.setContentType("text/xml;charset=utf-8");
        return viewResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("拦截中...");
                return true;
            }
        });
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                .favorPathExtension(true);

    }


    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return (factory -> {
            factory.addContextCustomizers(context -> {
                String relativePath = "springboot-view/src/main/webapp";
                File docFile = new File(relativePath);
                if (docFile.exists()) {
                    context.setDocBase(docFile.getAbsolutePath()); //解决maven多模块 JSP 无法读取的问题
                }
            });
        });
    }
}
