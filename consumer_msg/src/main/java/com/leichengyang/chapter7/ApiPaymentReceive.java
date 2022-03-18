package com.leichengyang.chapter7;

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
public class ApiPaymentReceive {

    @RabbitListener(queues = "api.payment")
    @RabbitHandler
    public void order(String message){
      log.info("api payment receive message: "+message);
    }

}
