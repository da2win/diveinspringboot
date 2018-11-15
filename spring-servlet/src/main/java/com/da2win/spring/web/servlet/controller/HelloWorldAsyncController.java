package com.da2win.spring.web.servlet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Hello World 异步 {@link RestController}
 *
 * @Author Darwin
 * @Date 2018/11/15 14:40
 */

@RestController
public class HelloWorldAsyncController {

    @GetMapping("/hello-world")
    public DeferredResult<String> helloWorld() {
        DeferredResult<String> deferredResult = new DeferredResult<>();

        //deferredResult.setResult("Hello, World");
        println("hello, world");

        deferredResult.onCompletion(() -> {
            println("执行结束");
        });

        deferredResult.onTimeout(() -> {
            println("执行超时");
        });
        return deferredResult;
    }

    private static void println(Object object) {
        String name = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + name + "]: " + object);

    }
}
