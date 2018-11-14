package com.da2win.web.config;

import com.da2win.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Rest {@link WebMvcConfigurer} 实现
 * @Author Darwin
 * @Date 2018/11/14 14:44
 */
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 不建议添加到 converters 的末尾
        //converters.add(new PropertiesHttpMessageConverter());
        converters.set(0, new PropertiesHttpMessageConverter()); // 添加至首位
    }
}
