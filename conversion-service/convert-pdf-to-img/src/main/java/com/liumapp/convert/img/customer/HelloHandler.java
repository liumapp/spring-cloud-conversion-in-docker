package com.liumapp.convert.img.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file HelloHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
@Component
@RabbitListener(queues = "hello")
public class HelloHandler {

    private static Logger logger = LoggerFactory.getLogger(HelloHandler.class);

    @RabbitHandler
    public void process (String msg) {
        logger.info("get msg from hello , the msg is : \n " + msg + "\n");
    }

}
