package com.liumapp.docker.conversion.service.business.controller;

import com.liumapp.convert.doc.Doc2PDF;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${convert.pdfPath}")
    private String pdfPath;

    @Value("${convert.docPath}")
    private String docPath;

    @Value("${convert.excelPath}")
    private String excelPath;

    @Value("${convert.imgPath}")
    private String imgPath;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("")
    public String index () {
        return "this is business module";
    }

    @RequestMapping("doc")
    public String sendDoc () {
        amqpTemplate.convertAndSend("");
        return "success";
    }

}
