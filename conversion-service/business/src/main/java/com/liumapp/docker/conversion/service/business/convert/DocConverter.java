package com.liumapp.docker.conversion.service.business.convert;

import com.alibaba.fastjson.JSON;
import com.liumapp.convert.doc.Doc2PDF;
import com.liumapp.pattern.conversion.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file DocConverter.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Component
@RabbitListener(queues = "doc-converter-queue")
public class DocConverter {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitHandler
    public void process (String msg) {
        DocPattern docPattern = JSON.parseObject(msg, DocPattern.class);
        PagePattern pagePattern = new PagePattern();
        pagePattern.setType("sys");
        pagePattern.setSysPdfPath(docPattern.getPdfPath());
        DocResultPattern docResultPattern = new DocResultPattern();
        try {
            Doc2PDF doc2PDF = new Doc2PDF();
            doc2PDF.doc2pdf(docPattern.getPdfPath() , docPattern.getSysPath());
        } catch (Exception e) {
            e.printStackTrace();
            // add faild info
            amqpTemplate.convertAndSend("doc-converter-result-queue", JSON.toJSONString(docResultPattern));
            return ;
        }
        // add success info and convert pdf to img
        amqpTemplate.convertAndSend("pic-converter-queue", JSON.toJSONString(pagePattern));
        amqpTemplate.convertAndSend("doc-converter-result-queue", JSON.toJSONString(docResultPattern));
    }

}
