package com.liumapp.convert.img;

import com.alibaba.fastjson.JSON;
import com.liumapp.convert.img.config.PatternConfig;
import com.liumapp.convert.img.config.RabbitConf;
import com.liumapp.convert.img.pattern.SimplePdfPattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author liumapp
 * @file SendOrderTest.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Converter.class,
        RabbitConf.class,
        PatternConfig.class})
public class SendOrderTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testSendPdf () {
        SimplePdfPattern simplePdfPattern = new SimplePdfPattern();
        simplePdfPattern.setPath("/usr/local/tomcat/project/convert-pdf-to-img/pdf/test.pdf");
        amqpTemplate.convertAndSend("simple-img-converter-queue", JSON.toJSONString(simplePdfPattern));
    }

    @Test
    public void testKeepSend () {
        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        System.out.println("send a msg to queue");
                        amqpTemplate.convertAndSend("hello" ,
                                "hello , now the time is : " + new Date());
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        sendThread.start();

        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
