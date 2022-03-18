package com.leichengyang.chapter8;

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
public class ApiCreditReceive {

    @RabbitListener(queues = "credit.bank")
    @RabbitHandler
    public void creditBank(String msg){
      log.info("credit.bank receive message: "+msg);
    }

    @RabbitListener(queues = "credit.finance")
    @RabbitHandler
    public void creditFinance(String msg){
        log.info("credit.finance receive message: "+msg);
    }

}
