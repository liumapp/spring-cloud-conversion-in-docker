package com.liumapp.convert.img.pattern;

import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file ImgPattern.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/7/18
 */
@Component
public class ImgPattern {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImgPattern{" +
                "path='" + path + '\'' +
                '}';
    }
}
