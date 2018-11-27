package com.da2win.diveinspringboot.externalized.configuration.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link PropertySources} {@link SpringApplicationRunListener} 实现
 *
 * @Author Darwin
 * @Date 2018/11/27 15:23
 */
public class ExtendPropertySourcesRunListener implements SpringApplicationRunListener, Ordered {

    private final SpringApplication application;
    private final String[] args;

    public ExtendPropertySourcesRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        // from-environmentPrepared: 0
        // application properties : 1
        // META-INF/default.properties: 7
        source.put("user.id", "0");
        MapPropertySource propertySource = new MapPropertySource("from-environmentPrepared", source);
        propertySources.addFirst(propertySource);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        // from-environmentPrepared: 0
        // application properties : 1
        // META-INF/default.properties: 7
        source.put("user.id", "39");
        MapPropertySource propertySource = new MapPropertySource("from-contextPrepared", source);
        propertySources.addFirst(propertySource);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        // from-environmentPrepared: 0
        // application properties : 1
        // META-INF/default.properties: 7
        source.put("user.id", "49");
        MapPropertySource propertySource = new MapPropertySource("from-contextLoaded", source);
        propertySources.addFirst(propertySource);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    @Override
    public int getOrder() {
        return new EventPublishingRunListener(application, args).getOrder() + 1;
    }
}
