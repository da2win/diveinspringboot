package com.da2win.diveinspringboot.externalized.configuration.bootstrap;

import com.da2win.diveinspringboot.externalized.configuration.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

/**
 * @Author Darwin
 * @Date 2018/11/26 16:17
 */
@EnableAutoConfiguration
public class ValueAnnotationBootstrap implements BeanFactoryAware {


    @Autowired
    @Qualifier(ENVIRONMENT_BEAN_NAME)
    private Environment environment;

    //public ValueAnnotationBootstrap(Environment environment) {
    //    this.environment = environment;
    //}
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.environment != beanFactory.getBean(ENVIRONMENT_BEAN_NAME, Environment.class)) {
            throw new IllegalStateException();
        }
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ValueAnnotationBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        User user = context.getBean("user", User.class);
        User user2 = context.getBean("user2", User.class);

        System.err.println("用户对象 : " + user);
        System.err.println("用户对象2 : " + user2);

        // 关闭上下文
        context.close();
    }

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name,
                     @Value("${user.age:${my.user.age:32}}") Integer age) {
        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Bean
//    @Autowired
    public User user2(
            //Environment environment
    ) {
        Long id = environment.getRequiredProperty("user.id", Long.class);
        String name = environment.getRequiredProperty("user.name");
        Integer age = environment.getProperty("user.age", Integer.class,
                environment.getProperty("my.user.age", Integer.class, 32));
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }


}
