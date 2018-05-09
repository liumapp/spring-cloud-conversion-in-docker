package com.liumapp.docker.conversion.service.business.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liumapp
 * @file RabbitConf.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Configuration
public class RabbitConf {

    @Bean
    public Queue ImgConverterQueue () {
        return new Queue ("img-converter-queue");
    }

    @Bean
    public Queue SimpleImgConverterQueue () {
        return new Queue("simple-img-converter-queue");
    }

    @Bean
    public Queue ImgConverterResultQueue () {
        return new Queue ("img-converter-result-queue");
    }

    @Bean
    public Queue OfficeConverterResultQueue () {
        return new Queue ("office-converter-result-queue");
    }

    @Bean
    public Queue OfficeConverterQueue () {
        return new Queue ("office-converter-queue");
    }

}
