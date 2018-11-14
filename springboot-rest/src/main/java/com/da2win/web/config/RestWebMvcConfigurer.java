package com.da2win.web.config;

import com.da2win.web.http.converter.properties.PropertiesHttpMessageConverter;
import com.da2win.web.support.PropertiesHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest {@link WebMvcConfigurer} 实现
 * @Author Darwin
 * @Date 2018/11/14 14:44
 */
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {

        // 获取当前 requestMappingHandlerAdapter 所有的 Resolver 对象
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();

        List<HandlerMethodArgumentResolver> newResolvers = new ArrayList(argumentResolvers.size() + 1);

        // 添加 PropertiesHandlerMethodArgumentResolver 到集合首位
        newResolvers.add(new PropertiesHandlerMethodArgumentResolver());

        // 添加 已注册的 Resolver 对象集合
        newResolvers.addAll(argumentResolvers);

        // 重新设置 Resolver 对象
        requestMappingHandlerAdapter.setArgumentResolvers(newResolvers);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 不建议添加到 converters 的末尾
        //converters.add(new PropertiesHttpMessageConverter());
        converters.set(0, new PropertiesHttpMessageConverter()); // 添加至首位
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 添加 PropertiesHandlerMethodArgumentResolver 到集合首位
        // 添加自定义的 HandlerMethodArgumentResolver 优先级低于内建(built-in)的实现
        //if (resolvers.isEmpty()) {
        //    resolvers.add(new PropertiesHandlerMethodArgumentResolver());
        //} else {
        //    resolvers.set(0, new PropertiesHandlerMethodArgumentResolver());
        //}
    }
}
