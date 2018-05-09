package com.liumapp.docker.conversion.service.business.convert;

import com.alibaba.fastjson.JSON;
import com.liumapp.convert.doc.Doc2PDF;
import com.liumapp.pattern.conversion.AllPagePattern;
import com.liumapp.pattern.conversion.DocPattern;
import com.liumapp.pattern.conversion.DocResultPattern;
import com.liumapp.pattern.conversion.FirstPagePattern;
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
        FirstPagePattern firstPagePattern = new FirstPagePattern();
        DocResultPattern docResultPattern = new DocResultPattern();
        AllPagePattern allPagePattern = new AllPagePattern();
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
        amqpTemplate.convertAndSend("first-pic-converter-queue", JSON.toJSONString(firstPagePattern));
        amqpTemplate.convertAndSend("all-pic-converter-queue", JSON.toJSONString(allPagePattern));
        amqpTemplate.convertAndSend("doc-converter-result-queue", JSON.toJSONString(docResultPattern));
    }

}
