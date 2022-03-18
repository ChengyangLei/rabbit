package com.leichengyang.chapter8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiCreditSendTest {


    @Autowired
    private ApiCreditSend apiCreditSend;

    @Test
    public void creditBank1() {
        Map<String,Object> headers=new HashMap<>();
        headers.put("type","crash");
        apiCreditSend.creditBank(headers,"银行授信（部分匹配）");
    }

    @Test
    public void creditBank2() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type","crash");
        headers.put("aging","fast");
        apiCreditSend.creditBank(headers,"银行授信（全部匹配）");
    }

    @Test
    public void creditFinance1() {
        Map<String,Object> headers=new HashMap<>();
        headers.put("type","crash");
        apiCreditSend.creditFinance(headers,"金融公司授信（部分匹配）");
    }

    @Test
    public void creditFinance2() {
        Map<String,Object> headers=new HashMap<>();
        headers.put("type","crash");
        headers.put("aging","fast");
        apiCreditSend.creditFinance(headers,"金融公司授信（全部匹配）");
    }


}