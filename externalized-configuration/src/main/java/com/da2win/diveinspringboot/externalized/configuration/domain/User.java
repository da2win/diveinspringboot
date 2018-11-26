package com.da2win.diveinspringboot.externalized.configuration.domain;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * 用户 doamain 类
 * @Author Darwin
 * @Date 2018/11/26 14:48
 */
public class User {
    private Long id;
    private String name;
    @Value("${user.age}")
    private Integer age;
    @Value("${user.desc:Hello, World}")
    private String desc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                '}';
    }
}