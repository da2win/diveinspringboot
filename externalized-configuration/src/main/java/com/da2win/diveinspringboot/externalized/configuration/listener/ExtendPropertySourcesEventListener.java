package com.da2win.diveinspringboot.externalized.configuration.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link PropertySources} {@link ApplicationListener} 实现, 监听 {@link ApplicationEnvironmentPreparedEvent}
 *
 * @Author Darwin
 * @Date 2018/11/27 16:02
 */
public class ExtendPropertySourcesEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        // from-environmentPrepared: 0
        // application properties : 1
        // META-INF/default.properties: 7
        source.put("user.id", "9");
        MapPropertySource propertySource = new MapPropertySource("from-ApplicationEnvironmentPreparedEvent", source);
        propertySources.addFirst(propertySource);
    }
}
