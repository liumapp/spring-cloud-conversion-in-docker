package com.liumapp.convert.img.customer;

import com.alibaba.fastjson.JSON;
import com.liumapp.convert.img.pattern.ImgConvertResultPattern;
import com.liumapp.convert.img.service.MultyPageConvertService;
import com.liumapp.convert.img.service.SinglePageConvertService;
import com.liumapp.convert.img.strategy.PageConvertStrategy;
import com.liumapp.convert.img.threadpools.ThreadPools;
import com.liumapp.pattern.conversion.AllPagePattern;
import com.liumapp.pattern.conversion.FirstPagePattern;
import com.liumapp.pattern.conversion.PagePattern;
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
 * @file PicConverterQueueHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Component
@RabbitListener(queues = "pic-converter-queue")
public class PicConverterQueueHandler implements PageConvertStrategy {

    private static Logger logger = LoggerFactory.getLogger(PicConverterQueueHandler.class);

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
        logger.info("get msg from pic-converter-queue , the msg is : \n " + msg + "\n");
        PagePattern pagePattern = JSON.parseObject(msg , PagePattern.class);
        Document document = new Document();
        String path = "";
        try {
            if (pagePattern.getType().equals("oss")) {
                // todo
                path = pagePattern.getOssPdfPath();
            } else {
                path = pagePattern.getSysPdfPath();
            }
            document.setFile(path);
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
                FirstPagePattern firstPagePattern = new FirstPagePattern();
                logger.info("begin single page convert in Simple img converter at " + new Date());
                boolean result = singlePageConvertService.convertFirstPage(document);
                if (result) {
                    firstPagePattern.setResult(true);
                    firstPagePattern.setMsg("simple convert the first page to img success");
                } else {
                    firstPagePattern.setResult(false);
                    firstPagePattern.setMsg("simple convert the first page to img failed");
                }
                amqpTemplate.convertAndSend("first-pic-converter-result-queue", JSON.toJSONString(firstPagePattern));
            }
        });
    }

    @Override
    public void multyPageConvert(Document document) {
        logger.info("begin multy page convert in Simple img converter at " + new Date());
        AllPagePattern allPagePattern = new AllPagePattern();
        boolean result = multyPageConvertService.convertMultyPage(document);
        if (result) {
            allPagePattern.setResult(true);
            allPagePattern.setMsg("simple convert the multy page to img success");
        } else {
            allPagePattern.setResult(false);
            allPagePattern.setMsg("simple convert the multy page to img failed");
        }
        amqpTemplate.convertAndSend("all-pic-converter-result-queue", JSON.toJSONString(allPagePattern));
    }
}
