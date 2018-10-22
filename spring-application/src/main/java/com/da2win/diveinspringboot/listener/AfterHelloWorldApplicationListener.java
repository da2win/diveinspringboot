package com.da2win.diveinspringboot.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * After HelloWorld {@link ApplicationListener} 监听 {@link ContextRefreshedEvent}
 */
public class AfterHelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("After HelloWorld : " + event.getApplicationContext().getId() + " timestamp :" + event.getTimestamp());
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
