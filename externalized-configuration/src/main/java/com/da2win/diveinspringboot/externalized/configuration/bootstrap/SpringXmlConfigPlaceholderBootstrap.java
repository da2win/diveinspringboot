package com.da2win.diveinspringboot.externalized.configuration.bootstrap;

import com.da2win.diveinspringboot.externalized.configuration.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring XML 占位符示例
 *
 * @Author Darwin
 * @Date 2018/11/26 14:57
 */
public class SpringXmlConfigPlaceholderBootstrap {

    public static void main(String[] args) {
        String[] locations = {"/META-INF/spring/spring-context.xml", "/META-INF/spring/user-context.xml"};

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);

        User user = applicationContext.getBean("user", User.class);

        System.err.println("用户对象 : " + user);


        // 关闭上下文
        applicationContext.close();

    }
}
