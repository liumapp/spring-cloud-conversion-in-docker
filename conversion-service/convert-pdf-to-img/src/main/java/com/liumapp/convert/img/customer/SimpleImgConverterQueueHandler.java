package com.liumapp.convert.img.customer;

import com.alibaba.fastjson.JSON;
import com.liumapp.convert.img.pattern.ImgConvertResultPattern;
import com.liumapp.convert.img.pattern.SimplePdfPattern;
import com.liumapp.convert.img.service.MultyPageConvertService;
import com.liumapp.convert.img.service.SinglePageConvertService;
import com.liumapp.convert.img.strategy.PageConvertStrategy;
import com.liumapp.convert.img.threadpools.ThreadPools;
import org.icepdf.core.pobjects.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liumapp
 * @file SimpleImgConverterQueueHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
@Component
@RabbitListener(queues = "simple-img-converter-queue")
public class SimpleImgConverterQueueHandler implements PageConvertStrategy {

    private static Logger logger = LoggerFactory.getLogger(SimpleImgConverterQueueHandler.class);

    @Autowired
    private SinglePageConvertService singlePageConvertService;

    @Autowired
    private MultyPageConvertService multyPageConvertService;

    @Autowired
    private ThreadPools threadPools;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitHandler
    public void process (String msg) {
        logger.info("get msg from simple-img-converter-queue , the msg is : \n " + msg + "\n");
        SimplePdfPattern simplePdfPattern = JSON.parseObject(msg, SimplePdfPattern.class);
        Document document = new Document();

        try {
            document.setFile(simplePdfPattern.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        singlePageConvert(document);
        multyPageConvert(document);
    }

    @Override
    public void singlePageConvert(Document document) {
        ThreadPoolExecutor threadPoolExecutor = threadPools.getThreadPoolExecutor();
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ImgConvertResultPattern imgConvertResultPattern = new ImgConvertResultPattern();
                logger.info("begin single page convert in Simple img converter at " + new Date());
                boolean result = singlePageConvertService.convertFirstPage(document);
                if (result) {
                    imgConvertResultPattern.setResult(true);
                    imgConvertResultPattern.setMsg("simple convert the first page to img success");
                } else {
                    imgConvertResultPattern.setResult(false);
                    imgConvertResultPattern.setMsg("simple convert the first page to img failed");
                }
                amqpTemplate.convertAndSend("img-converter-result-queue", JSON.toJSONString(imgConvertResultPattern));
            }
        });
    }

    @Override
    public void multyPageConvert(Document document) {
        logger.info("begin multy page convert in Simple img converter at " + new Date());
        ImgConvertResultPattern imgConvertResultPattern = new ImgConvertResultPattern();
        boolean result = multyPageConvertService.convertMultyPage(document);
        if (result) {
            imgConvertResultPattern.setResult(true);
            imgConvertResultPattern.setMsg("simple convert the multy page to img success");
        } else {
            imgConvertResultPattern.setResult(false);
            imgConvertResultPattern.setMsg("simple convert the multy page to img failed");
        }
        amqpTemplate.convertAndSend("img-converter-result-queue", JSON.toJSONString(imgConvertResultPattern));
    }


}
