package com.da2win.diveinspringboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *  java 7 for 循环实现 {@link CalculateService}
 */
@Profile("Java7")
@Service
public class Java7CalculateSerivce implements CalculateService{
    @Override
    public Integer sum(Integer... values) {

        System.out.println("java 7 for 循环实现");

        int sum = 0;

        for (Integer integer : values) {
            sum += integer;
        }

        return sum;
    }

    public static void main(String[] args) {
        CalculateService calculateService = new Java7CalculateSerivce();
        System.out.println(calculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
