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
    public Queue FirstPicConverterQueue () {
        return new Queue ("first-pic-converter-queue");
    }

    @Bean
    public Queue FirstPicConverterResultQueue () {
        return new Queue ("first-pic-converter-result-queue");
    }

    @Bean
    public Queue AllPicConverterQueue () {
        return new Queue ("all-pic-converter-queue");
    }

    @Bean
    public Queue AllPicConverterResultQueue () {
        return new Queue ("all-pic-converter-result-queue");
    }

    @Bean
    public Queue DocConverterResultQueue () {
        return new Queue ("doc-converter-result-queue");
    }

    @Bean
    public Queue DocConverterQueue () {
        return new Queue ("doc-converter-queue");
    }

    @Bean
    public Queue ExcelConverterQueue () {
        return new Queue ("excel-converter-queue");
    }

    @Bean
    public Queue ExcelConverterResultQueue () {
        return new Queue ("excel-converter-result-queue");
    }

}
