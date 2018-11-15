package com.da2win.spring.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

/**
 * 异步 Servlet
 *
 * @Author Darwin
 * @Date 2018/11/15 14:21
 */
@WebServlet(
        asyncSupported = true, // 激活异步特性
        name = "asyncServlet",
        urlPatterns = "/async-servlet"
)
public class AsyncServlet extends HttpServlet {

    // 覆盖service方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 判断是否支持异步
        if (req.isAsyncSupported()) {

            //创建 AsyncContext
            AsyncContext asyncContext = req.startAsync();

            // 设置超时时间
            asyncContext.setTimeout(50);

            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent event) throws IOException {
                    println("执行完成");
                }

                @Override
                public void onTimeout(AsyncEvent event) throws IOException {
                    HttpServletResponse servletResponse = (HttpServletResponse) event.getSuppliedResponse();

                    servletResponse.setStatus(SC_SERVICE_UNAVAILABLE);
                    println("执行超时");
                }

                @Override
                public void onError(AsyncEvent event) throws IOException {
                    println("执行错误");
                }

                @Override
                public void onStartAsync(AsyncEvent event) throws IOException {
                    println("开始执行");
                }
            });

            println("Hello, World");

            //ServletResponse response = asyncContext.getResponse();
            //
            //response.setContentType("text/plain;charset=utf-8");
            //
            //// 获取字符输出流
            //PrintWriter writer = response.getWriter();
            //
            //writer.println("Hello, World");
            //
            //writer.flush();
        }


    }

    private static void println(Object object) {
        String name = Thread.currentThread().getName();
        System.out.println("AsyncServlet[" + name + "]: " + object);

    }
}
