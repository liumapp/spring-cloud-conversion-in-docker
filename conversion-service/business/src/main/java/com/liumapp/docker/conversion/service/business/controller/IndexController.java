package com.liumapp.docker.conversion.service.business.controller;

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

    @GetMapping("")
    public String index () {
        return "this is business module";
    }

    @RequestMapping("sendDoc")
    public String sendDoc () {
        return "success";
    }

}
