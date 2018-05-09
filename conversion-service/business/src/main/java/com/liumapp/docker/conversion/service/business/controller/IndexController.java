package com.liumapp.docker.conversion.service.business.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumapp
 * @file IndexController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("")
    public String index () {
        return "this is business module";
    }

    @RequestMapping("sendDoc")
    public String sendDoc () {
        amqpTemplate.convertAndSend("simple-img-converter-queue" , "a");
        return "success";
    }

}
