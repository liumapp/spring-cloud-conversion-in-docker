package com.liumapp.docker.conversion.service.business.controller;

import com.alibaba.fastjson.JSON;
import com.liumapp.pattern.conversion.DocPattern;
import com.liumapp.pattern.conversion.ExcelPattern;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    /**
     * 立即返回结果
     * @return string
     */
    @RequestMapping("doc")
    public String sendDoc () throws InterruptedException {
        DocPattern docPattern = new DocPattern();
        docPattern.setType("sys");
        docPattern.setPdfPath(pdfPath + "/output" + new Date() + ".pdf");
        docPattern.setSysPath(docPath + "/test.doc");
        amqpTemplate.convertAndSend("doc-converter-queue", JSON.toJSONString(docPattern));
        return "success";
    }

    /**
     * 立即返回结果
     * @return string
     */
    @RequestMapping("excel")
    public String sendExcel () throws InterruptedException {
        ExcelPattern excelPattern = new ExcelPattern();
        excelPattern.setType("sys");
        excelPattern.setPdfPath(pdfPath + "/output" + new Date() + ".pdf");
        excelPattern.setSysPath(excelPath + "/test.xlsx");
        amqpTemplate.convertAndSend("excel-converter-queue", JSON.toJSONString(excelPattern));
        return "success";
    }

}
