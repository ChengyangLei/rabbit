package com.leichengyang.chapter6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class ProducerSend {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PaymentNotifySender paymentNotifySender;

    @Test
    public void sendMsgByTopics() {
//        for (int i =0;i<5;i++){
//            long timeMillis = System.currentTimeMillis();
//            rabbitTemplate.convertAndSend("notify.payment",timeMillis);
//        }

        long timeMillis = System.currentTimeMillis();
        paymentNotifySender.send("支付订单号 : " +timeMillis);
        System.out.println("支付订单号 : " + timeMillis);
    }
}
