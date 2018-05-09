package com.liumapp.docker.conversion.service.business.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file HandleExcelResult.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Component
@RabbitListener(queues = "excel-converter-result-queue")
public class HandleExcelResult {

    private static Logger logger = LoggerFactory.getLogger(HandleExcelResult.class);

    @RabbitHandler
    public void process (String msg) {

    }

}
