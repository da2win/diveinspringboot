package com.da2win.diveinspringboot.externalized.configuration.bootstrap;

import com.da2win.diveinspringboot.externalized.configuration.domain.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link ConfigurationProperties}
 * @Author Darwin
 * @Date 2018/11/27 10:31
 */
@EnableAutoConfiguration
@EnableConfigurationProperties
public class ConfigurationPropertiesBootstrap {

    @Bean
    @ConfigurationProperties(prefix = "user")
    public User user() {
        return new User();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConfigurationPropertiesBootstrap.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);

        //User user = context.getBean("user", User.class);
        User user = context.getBean(User.class);

        System.out.println("用户对象 : " + user);

        System.err.printf("System.getProperty(\"%s\") : %s \n", "user.name", System.getProperty("user.name"));

        // 关闭上下文
        context.close();
    }
}
