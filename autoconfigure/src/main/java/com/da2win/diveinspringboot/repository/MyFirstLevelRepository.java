package com.da2win.diveinspringboot.repository;

import com.da2win.diveinspringboot.annotation.FirstLevelRepository;
import com.da2win.diveinspringboot.annotation.SecondLevelRepository;

/**
 * @See {@link FirstLevelRepository}
 */
@SecondLevelRepository(value = "myFirstLevelRepository")
public class MyFirstLevelRepository {
}
