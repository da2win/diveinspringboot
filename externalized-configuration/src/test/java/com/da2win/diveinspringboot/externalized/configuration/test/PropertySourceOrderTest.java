package com.da2win.diveinspringboot.externalized.configuration.test;

import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 外部化配置属性源 @{link PropertySource} 顺序的测试用例
 *
 * @Author Darwin
 * @Date 2018/11/27 14:39
 */
@TestPropertySource(
        //properties = "user.id=9",
        locations = "classpath:META-INF/default.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(
        //properties = "user.id=8",
        classes = {PropertySourceOrderTest.class, PropertySourceOrderTest.MyConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PropertySourceOrderTest {

    @Configuration
    @PropertySource(name = "test-property-source", value = "classpath:META-INF/test.properties")
    public static class MyConfig {

    }

    @Value("${user.id}")
    private Long userId;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Test
    public void testEnvironment() {
        Assert.assertSame(applicationContext.getEnvironment(), environment);
    }

    @Test
    public void testUserId() {
        Assert.assertEquals(new Long(7L), userId);
    }

    @Test
    public void testPropertyResouces() {
        environment.getPropertySources().forEach(propertySource -> {
            System.out.printf("PropertySource([名称:%s] : %s\n", propertySource.getName(), propertySource);
            System.out.println();
        });
    }
}
