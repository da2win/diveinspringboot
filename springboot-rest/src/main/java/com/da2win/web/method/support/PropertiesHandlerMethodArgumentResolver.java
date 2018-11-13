package com.da2win.web.method.support;

import com.da2win.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * {@link Properties} {@link HandlerMethodArgumentResolver}
 *
 * @Author Darwin
 * @Date 2018/11/14 16:44
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        // 复用 PropertiesHttpMessageConverter
        PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();


        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;

        // Servlet Request API 实现
        HttpServletRequest request = (HttpServletRequest) servletWebRequest.getNativeRequest();

        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);


        //String contentType = request.getHeader("Content-Type");
        //// 获取
        //MediaType mediaType = MediaType.parseMediaType(contentType);
        //
        //Charset charset = mediaType.getCharset() == null ? Charset.forName("UTF-8") : mediaType.getCharset();
        //
        //InputStream inputStream = request.getInputStream();
        //
        //InputStreamReader reader = new InputStreamReader(inputStream, charset);
        //
        //Properties properties = new Properties();
        //
        //// 加载字符流
        //properties.load(reader);

        return converter.read(null, null, inputMessage);
    }
}
