package com.da2win.diveinspringboot.configuration;

import com.da2win.diveinspringboot.annotation.EnableHelloWorld;
import com.da2win.diveinspringboot.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld 自动装配
 */
@Configuration // Spring 模式注解装配
@EnableHelloWorld // Spring 模块装配
@ConditionalOnSystemProperty(name = "username", value = "da2wi") // 条件装配
public class HelloWorldAutoConfiguration {

}
