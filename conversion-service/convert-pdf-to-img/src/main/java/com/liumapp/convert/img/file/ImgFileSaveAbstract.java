package com.liumapp.convert.img.file;

import com.liumapp.convert.img.pattern.ImgPattern;
import com.liumapp.convert.img.strategy.ImgFileSaveStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author liumapp
 * @file ImgFileSaveAbstract.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/8/18
 */
public abstract class ImgFileSaveAbstract implements ImgFileSaveStrategy {

    @Autowired
    private ImgPattern imgPattern;

    @Override
    public File createFile() {
        Random random = new Random();
        String fileName = "pdfConverter" + System.currentTimeMillis() + "" + random.nextInt(1000)+".jpg";
        String path = imgPattern.getPath() + useDatePath() + "/";

        File dir = new File(path);
        if (!dir.exists() && !dir.isDirectory())
            dir.mkdir();

        File file = new File(imgPattern.getPath() + useDatePath() + "/" + fileName);
        return file;
    }

    @Override
    public String useDatePath() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
