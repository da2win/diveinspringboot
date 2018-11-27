package com.da2win.diveinspringboot.externalized.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySources;

/**
 * 扩展 {@link PropertySources} 引导类
 *
 * @Author Darwin
 * @Date 2018/11/27 15:40
 */
@EnableAutoConfiguration
@PropertySource(name = "from classpath", value = "classpath:META-INF/default.properties")
@Configuration
public class ExtendPropertySourcesBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ExtendPropertySourcesBootstrap.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .properties("user.id=99")
                .run(of("--user.id=88")); // command line arguments

        // 获取 Environment
        ConfigurableEnvironment environment = context.getEnvironment();

        environment.getPropertySources().forEach(propertySource -> {
            System.out.printf("PropertySource([名称:%s] : %s\n", propertySource.getName(), propertySource);
            System.out.println();
        });

        System.err.printf("用户对象id : %d\n", environment.getProperty("user.id", Long.class));

        // 关闭上下文
        context.close();
    }

    private static <T> T[] of(T... args) {
        return args;
    }
}
