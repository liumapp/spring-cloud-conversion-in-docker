package com.liumapp.convert.img.service;

import org.icepdf.core.pobjects.Document;

/**
 * @author liumapp
 * @file SinglePageConvertService.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
public interface SinglePageConvertService {

    public boolean convertFirstPage(Document document);

}
