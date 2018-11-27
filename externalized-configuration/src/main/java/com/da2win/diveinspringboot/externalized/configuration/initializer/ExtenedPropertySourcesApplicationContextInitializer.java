package com.da2win.diveinspringboot.externalized.configuration.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link PropertySources} {@link ApplicationContextInitializer}
 * @Author Darwin
 * @Date 2018/11/27 17:00
 */
public class ExtenedPropertySourcesApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        // from-environmentPrepared: 0
        // application properties : 1
        // META-INF/default.properties: 7
        source.put("user.id", "29");
        MapPropertySource propertySource = new MapPropertySource("from-initialize", source);
        propertySources.addFirst(propertySource);
    }
}
