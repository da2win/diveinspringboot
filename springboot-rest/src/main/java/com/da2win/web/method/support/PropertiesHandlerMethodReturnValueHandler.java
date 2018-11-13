package com.da2win.web.method.support;

import com.da2win.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * {@link Properties} {@link HandlerMethodReturnValueHandler}
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 判断方法的返回类型, 是否与 Properties 类型匹配
        return Properties.class.equals(returnType.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;

        // Servlet Request API 实现
        HttpServletRequest request = (HttpServletRequest) servletWebRequest.getNativeRequest();
        // 获取 Servlet Response API
        HttpServletResponse response = (HttpServletResponse) servletWebRequest.getNativeResponse();

        String contentType = request.getHeader("Content-Type");
        // 获取
        MediaType mediaType = MediaType.parseMediaType(contentType);

        // 强制转换
        Properties properties = (Properties) returnValue;

        PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();

        HttpOutputMessage message = new ServletServerHttpResponse(response);

        // 通过 PropertiesHttpMessageConverter 输出
        converter.write(properties, mediaType, message);
        // 告知 Spring Web MVC 当前请求已经处理完毕
        mavContainer.setRequestHandled(true);
    }
}
