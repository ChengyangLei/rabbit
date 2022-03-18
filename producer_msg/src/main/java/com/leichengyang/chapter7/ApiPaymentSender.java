package com.leichengyang.chapter7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@Slf4j
@Component
public class ApiPaymentSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void order(String msg){
        log.info("api.payment.order send message: "+msg);
        amqpTemplate.convertAndSend("paymentExchange","api.payment.order",msg);
    }

    public void orderQuery(String msg){
        log.info("api.payment.order.query send message: "+ msg);
        amqpTemplate.convertAndSend("paymentExchange","api.payment.order.query",msg);
    }

    public void orderDetailQuery(String msg){
        log.info("api.payment.order.detail.query send message: "+msg);
        amqpTemplate.convertAndSend("paymentExchange","api.payment.order.detail.query",msg);
    }


}
