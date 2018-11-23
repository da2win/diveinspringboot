package com.da2win.web.reactive.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @Author Darwin
 * @Date 2018/11/23 14:52
 */
@SpringBootApplication
@Controller
public class WebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @RequestMapping("/mvc")
    @ResponseBody
    public String mvc() {
        println("MVC");
        return "MVC";
    }
    @RequestMapping("/mono")
    @ResponseBody
    public Mono<String> mono() {
        println("Mono");
        return Mono.just("Mono");
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        //return RouterFunctions.route(serverRequest -> { // 判断请求是否匹配
        //    URI uri = serverRequest.uri();
        //    return "/hello-world".equals(uri.getPath());
        //}, serverRequest -> { // 绑定实现
        //    return ServerResponse.status(HttpStatus.OK)
        //            .body(Mono.just("Hello, World"), String.class);
        //});

        return route(GET("/hello-world"), this::helloWorld);
    }

    public Mono<ServerResponse> helloWorld(ServerRequest serverRequest) {
        println("Hello, World");
        return ServerResponse.status(HttpStatus.OK)
                .body(Mono.just("Hello, World"), String.class);
    }

    public static void println(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] : " + message);
    }
}
