package com.liumapp.docker.conversion.service.business.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file HandleFirstPicResult.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Component
@RabbitListener(queues = "first-pic-converter-result-queue")
public class HandleFirstPicResult {

    private static Logger logger = LoggerFactory.getLogger(HandleFirstPicResult.class);

    @RabbitHandler
    public void process (String msg) {
        logger.info("get info from first-pic-converter-result-queue , msg is : " + msg);
    }

}
