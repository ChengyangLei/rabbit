package com.leichengyang.chapter7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiPaymentSenderTest {

    @Autowired
    private ApiPaymentSender apiPaymentSender;

    @Test
    public void order() {
        apiPaymentSender.order("订单管理");
    }

    @Test
    public void orderQuery() {
        apiPaymentSender.orderQuery("订单查询");
    }

    @Test
    public void orderDetailQuery() {
        apiPaymentSender.orderDetailQuery("订单详情查询");
    }
}