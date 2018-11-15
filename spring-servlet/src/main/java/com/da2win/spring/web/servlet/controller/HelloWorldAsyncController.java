package com.da2win.spring.web.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Hello World 异步 {@link RestController}
 *
 * @Author Darwin
 * @Date 2018/11/15 14:40
 */

@RestController
@EnableScheduling
public class HelloWorldAsyncController {

    private final BlockingQueue<DeferredResult<String>> queue = new ArrayBlockingQueue<>(5);

    // 超时随机数
    private final Random random = new Random();

    @Scheduled(fixedRate = 5000)
    public void process() throws InterruptedException { // 定时操作
        DeferredResult<String> result = null;
        do {
            result = queue.take();
            // 随机超时时间
            long timeout = random.nextInt(100);
            // 模拟等待时间, RPC 或者 DB 查询
            Thread.sleep(timeout);
            // 计算结果
            result.setResult("Hello,World");
            println("执行计算结果, 消耗: " + timeout + "ms. ");

        } while (result != null);
    }

    @GetMapping("/hello-world")
    public DeferredResult<String> helloWorld() {
        DeferredResult<String> deferredResult = new DeferredResult(50L);

        //deferredResult.setResult("Hello, World");
        // 入队操作
        queue.offer(deferredResult);


        println("hello, world");

        deferredResult.onCompletion(() -> {
            println("执行结束xxxx");
        });

        deferredResult.onTimeout(() -> {
            println("执行超时xxxx");
        });
        return deferredResult;
    }

    @GetMapping("/callable-hello-world")
    public Callable<String> callableHelloWorld() {
        final long startTime = System.currentTimeMillis();

        return () -> {
            long costTime = System.currentTimeMillis() - startTime;
            println("执行计算结果, 消耗: " + costTime + "ms. ");
            return "Hello, World";
        };
    }

    private static void println(Object object) {
        String name = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + name + "]: " + object);

    }
}
