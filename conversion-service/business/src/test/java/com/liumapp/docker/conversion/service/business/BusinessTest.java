package com.liumapp.docker.conversion.service.business;

import com.liumapp.convert.doc.Doc2PDF;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liumapp
 * @file BusinessTest.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 5/9/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Business.class})
public class BusinessTest{

    @Value("${convert.pdfPath}")
    private String pdfPath;

    @Value("${convert.docPath}")
    private String docPath;

    @Test
    public void testDoc () {
        Doc2PDF doc2PDF = new Doc2PDF();
        doc2PDF.doc2pdf(pdfPath + "/test.pdf" , docPath + "/test.doc");
    }

}
