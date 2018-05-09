package com.liumapp.docker.conversion.service.business.convert;

import com.alibaba.fastjson.JSON;
import com.liumapp.convert.cell.CellToPDF;
import com.liumapp.pattern.conversion.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file ExcelConverter.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@Component
@RabbitListener(queues = "excel-converter-queue")
public class ExcelConverter {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitHandler
    public void process (String msg) {
        ExcelPattern excelPattern = JSON.parseObject(msg , ExcelPattern.class);
        ExcelResultPattern excelResultPattern = new ExcelResultPattern();
        PagePattern pagePattern = new PagePattern();
        try {
            CellToPDF cellToPDF = new CellToPDF();
            cellToPDF.excel2pdf(excelPattern.getPdfPath() , excelPattern.getPdfPath());
        } catch (Exception e) {
            // add failed info
            amqpTemplate.convertAndSend("excel-converter-result-queue", excelResultPattern);
            e.printStackTrace();
            return ;
        }
        // add success info , and convert pdf into pic
        amqpTemplate.convertAndSend("excel-converter-result-queue", excelResultPattern);
        amqpTemplate.convertAndSend("pic-converter-queue", JSON.toJSONString(pagePattern));
    }

}
