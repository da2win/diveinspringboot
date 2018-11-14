package com.da2win.web.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * {@link Properties} {@link HttpMessageConverter}
 * @Author Darwin
 * @Date 2018/11/14 14:29
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        // 设置支持的 MediaType
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // Properties -> String
        // OutputStream -> Writer

        MediaType contentType = outputMessage.getHeaders().getContentType();
        Charset charset = contentType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;

        // 字节输出流
        OutputStream outputStream = outputMessage.getBody();
        // 字符输出流
        Writer writer = new OutputStreamWriter(outputStream, charset);

        // properties 写入到字符输出流
        properties.store(writer, "From PropertiesHttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        // 字符流 -> 字符编码
        // 从请求头 Content-Type 解析编码
        HttpHeaders headers = inputMessage.getHeaders();
        MediaType contentType = headers.getContentType();

        // 获取字符编码
        Charset charset = contentType.getCharset();
        // 当不存在时, 使用 UTF-8
        charset = charset == null ? Charset.forName("UTF-8") : charset;

        // 字节流
        InputStream inputStream = inputMessage.getBody();

        InputStreamReader reader = new InputStreamReader(inputStream, charset);

        Properties properties = new Properties();

        // 加载字符流
        properties.load(reader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }
}
