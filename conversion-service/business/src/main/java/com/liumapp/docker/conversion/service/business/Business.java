package com.liumapp.docker.conversion.service.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liumapp
 * @file Business.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Business {

    public static void main(String[] args) {
        SpringApplication.run(Business.class , args);
    }

}
