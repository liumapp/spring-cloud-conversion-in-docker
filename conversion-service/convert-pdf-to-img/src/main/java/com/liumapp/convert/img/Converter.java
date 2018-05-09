package com.liumapp.convert.img;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author liumapp
 * @file Converter.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Converter {

    public static void main (String[] args) {
        SpringApplication.run(Converter.class, args);
    }

}
