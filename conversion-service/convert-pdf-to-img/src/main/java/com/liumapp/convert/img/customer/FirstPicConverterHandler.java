package com.liumapp.convert.img.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file FirstPicConverterHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Component
@RabbitListener(queues = "first-pic-converter-queue")
public class FirstPicConverterHandler {

    @RabbitHandler
    public void process (String msg) {

    }

}
