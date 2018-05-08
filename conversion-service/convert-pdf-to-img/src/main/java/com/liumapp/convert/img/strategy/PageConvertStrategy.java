package com.liumapp.convert.img.strategy;

import org.icepdf.core.pobjects.Document;

/**
 * @author liumapp
 * @file PageConvertStrategy.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/8/18
 */
public interface PageConvertStrategy {

    public void singlePageConvert (Document document);

    public void multyPageConvert (Document document);

}
