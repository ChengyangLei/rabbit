package com.leichengyang.chapter6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@Slf4j
@Component
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceive {

    @RabbitHandler
    public void receive(String msg){
        log.info("notify.payment receive message: "+msg);
    }

}
