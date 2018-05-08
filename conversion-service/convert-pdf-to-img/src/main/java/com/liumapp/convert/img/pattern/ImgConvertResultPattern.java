package com.liumapp.convert.img.pattern;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author liumapp
 * @file ImgConvertResultPattern.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/8/18
 */
public class ImgConvertResultPattern implements Serializable {

    public boolean result;

    public String msg;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ImgConvertResultPattern{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                '}';
    }
}
