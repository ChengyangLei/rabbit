package com.leichengyang.chapter9;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiReportSenderTest {

    @Autowired
    private ApiReportSender apiReportSender;


    @Test
    public void generateReport() {
        apiReportSender.generateReport("开始生成报表");
    }
}