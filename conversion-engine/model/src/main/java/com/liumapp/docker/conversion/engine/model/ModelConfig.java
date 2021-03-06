package com.liumapp.docker.conversion.engine.model;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
/**
 * @author liumapp
 * @file ModelConfig.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 3/22/18
 */
@Configuration
@MapperScan(basePackages = "com.liumapp.docker.conversion.engine.model.mapper")
public class ModelConfig {


}
