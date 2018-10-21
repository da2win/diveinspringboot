package com.da2win.diveinspringboot.bootstrap;

import com.da2win.diveinspringboot.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link CalculateService}
 */
@SpringBootApplication(scanBasePackages = "com.da2win.diveinspringboot.service")
public class CalculateServiceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("Java8")
                .run(args);
        // myFirstLevelRepository Bean 是否存在
        CalculateService calculateService = context.getBean(CalculateService.class);
        System.out.println("calculateService.sum(1...10) : " + calculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        // 关闭上下文
        context.close();
    }
}
