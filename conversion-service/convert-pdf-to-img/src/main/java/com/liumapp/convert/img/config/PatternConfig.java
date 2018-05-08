package com.liumapp.convert.img.config;

import com.liumapp.convert.img.pattern.ImgPattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liumapp
 * @file PatternConfig.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
@Configuration
public class PatternConfig {

    @Bean
    @ConfigurationProperties(prefix = "liumapp.convert.img")
    public ImgPattern imgPattern () {
        ImgPattern imgPattern = new ImgPattern();
        return imgPattern;
    }

}
