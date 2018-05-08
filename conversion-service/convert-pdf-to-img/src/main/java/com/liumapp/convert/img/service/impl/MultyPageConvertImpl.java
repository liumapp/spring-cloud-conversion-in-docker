package com.liumapp.convert.img.service.impl;

import com.liumapp.convert.img.file.ImgFileSaveAbstract;
import com.liumapp.convert.img.pattern.ImgPattern;
import com.liumapp.convert.img.service.MultyPageConvertService;
import com.liumapp.convert.img.strategy.ImgFileSaveStrategy;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author liumapp
 * @file MultyPageConvertImpl.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
@Service
public class MultyPageConvertImpl extends ImgFileSaveAbstract implements MultyPageConvertService {

    /**
     * begin multy page convert from the second page .
     * @param document
     */
    @Override
    public boolean convertMultyPage(Document document) {
        BufferedImage image = null;
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;
        for (int i = 1; i < document.getNumberOfPages(); i++) {
            try {
                image = (BufferedImage)
                        document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;
                ImageIO.write(rendImage, "png", createFile());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            image.flush();
        }
        document.dispose();
        System.gc();
        return true;
    }

}
