package com.da2win.spring.reactive.loader;

import java.util.concurrent.CompletableFuture;

/**
 * 链式的数据加载器
 * @Author Darwin
 * @Date 2018/11/21 17:04
 */
public class ChainDataLoader extends DataLoader {
    protected void doLoad() {
        CompletableFuture
                .runAsync(super::loadConfigurations)
                .thenRun(super::loadUsers)
                .thenRun(super::loadOrders)
                .whenComplete((result, throwable) -> { // 完成时回调
                    System.out.println("[线程 : "+Thread.currentThread().getName()+"] 加载完成");
                })
                .join(); // 等待完成
    }
    public static void main(String[] args) {
        new ChainDataLoader().load();
    }
}