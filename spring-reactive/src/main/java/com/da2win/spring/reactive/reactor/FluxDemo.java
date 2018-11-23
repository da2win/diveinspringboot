package com.da2win.spring.reactive.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Flux 实例
 *
 * @Author Darwin
 * @Date 2018/11/23 10:17
 */
public class FluxDemo {

    public static void main(String[] args)  {
        // 非阻塞序列
        println("运行中...");
        Flux.just("A", "B", "C") // A -> B -> C
                //.publishOn(Schedulers.elastic()) // 线程池的切换
                .map(value -> "+" + value) // A -> +A 转换
                //.subscribe(
                //        FluxDemo::println, // 数据消费 = onNext(T)
                //        FluxDemo::println, // 异常处理 = onError(Throwable)
                //        () -> {
                //            println("完成操作");
                //        }, // 完成回调 = onComplete
                //        subscription -> {
                //            subscription.cancel(); // 取消上游传输数据到下游
                //            subscription.request(Integer.MAX_VALUE); // n: 请求元素的数量
                //
                //        } // 背压操作
                //);
                .subscribe(new Subscriber<String>() {

                    private Subscription subscription;

                    private int count = 0;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        if (count == 2) {
                            throw new RuntimeException("故意抛异常");
                        }
                        println(s);
                        count++;
                        subscription.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        println(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        println("完成操作");
                    }
                });


    }


    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程: " + threadName + "] " + object);
    }
}
