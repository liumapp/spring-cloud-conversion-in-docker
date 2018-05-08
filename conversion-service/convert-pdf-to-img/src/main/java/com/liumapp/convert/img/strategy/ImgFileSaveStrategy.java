package com.liumapp.convert.img.strategy;

import java.io.File;

/**
 * @author liumapp
 * @file ImgFileSaveStrategy.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/8/18
 */
public interface ImgFileSaveStrategy {

    public File createFile ();

    public String useDatePath ();

}
