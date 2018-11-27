package com.da2win.diveinspringboot.externalized.configuration.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link PropertySources}
 * @Author Darwin
 * @Date 2018/11/27 16:43
 */
public class ExtendPropertyResourcesEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        // from-environmentPrepared: 0
        // application properties : 1
        // META-INF/default.properties: 7
        source.put("user.id", "19");
        MapPropertySource propertySource = new MapPropertySource("from-postProcessEnvironment", source);
        propertySources.addFirst(propertySource);
    }

    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1;
    }
}
