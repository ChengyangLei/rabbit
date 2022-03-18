package com.leichengyang.chapter6;

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
public class PaymentNotifySender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg){
        amqpTemplate.convertAndSend("notify.payment",msg);
        log.info("notify.payment send message: "+msg);
    }

}
