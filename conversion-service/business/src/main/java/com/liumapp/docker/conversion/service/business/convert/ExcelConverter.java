package com.liumapp.docker.conversion.service.business.convert;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file ExcelConverter.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Component
@RabbitListener(queues = "excel-converter-queue")
public class ExcelConverter {

    @RabbitHandler
    public void process () {

    }

}
