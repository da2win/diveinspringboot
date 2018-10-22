package com.da2win.diveinspringboot;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 应用事件引导类
 *
 */
public class SpringApplicationEventBootstrap {

    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // register application event listener
        context.addApplicationListener(event -> {
            System.out.println("监听到事件: " + event);
        });

        // start context
        context.refresh();

        context.publishEvent("HelloWorld");
        context.publishEvent("2018");
        context.publishEvent(new ApplicationEvent("Da2win") {

        });

        // close context
        context.close();
    }
}
