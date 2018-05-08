package com.liumapp.convert.img.pattern;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author liumapp
 * @file BusinessPdfPattern.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/8/18
 */
@Component
public class BusinessPdfPattern implements Serializable {

    /**
     * which table
     */
    private String tableName;

    /**
     * which table filed
     */
    private String filedName;

    /**
     * the filed value
     */
    private Long id;

    /**
     * the pdf path
     */
    private String path;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "BusinessPdfPattern{" +
                "tableName='" + tableName + '\'' +
                ", filedName='" + filedName + '\'' +
                ", id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
