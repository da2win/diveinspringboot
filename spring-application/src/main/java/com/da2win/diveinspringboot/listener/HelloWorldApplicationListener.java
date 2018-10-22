package com.da2win.diveinspringboot.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * HelloWolrd {@link ApplicationListener} 监听 {@link ContextRefreshedEvent}
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("HelloWorld : " + event.getApplicationContext().getId() + " timestamp :" + event.getTimestamp());
    }
}
