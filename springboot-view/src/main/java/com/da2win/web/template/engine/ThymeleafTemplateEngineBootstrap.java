package com.da2win.web.template.engine;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ThymeleafTemplateEngineBootstrap {

    public static void main(String[] args) throws IOException {
        // 构建引擎
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // 创建渲染上下文
        Context context = new Context();
        context.setVariable("message", "Hello, World");

        // 读取内容从 classpath:/templates/thymeleaf/hello-world.html
        // ResourceLoader
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // 通过 classpath:/templates/thymeleaf/hello-world.html 获取 Resource
        Resource resource = resourceLoader.getResource("classpath:/templates/thymeleaf/hello-world.html");
        File templateFile = resource.getFile();

        FileInputStream inputStream = new FileInputStream(templateFile);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Copy
        IOUtils.copy(inputStream, outputStream);

        String content = outputStream.toString("UTF-8");

        inputStream.close();


        // 模板的内容
        //String content = "<p th:text=\"${message}\">!!!</p>";
        // 渲染处理结果
        String result = templateEngine.process(content, context);
        // 输出渲染结果
        System.out.println(result);
    }
}
