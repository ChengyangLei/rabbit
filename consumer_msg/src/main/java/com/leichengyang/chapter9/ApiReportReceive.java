package com.leichengyang.chapter9;

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
public class ApiReportReceive {


    @RabbitListener(queues = "api.report.payment")
    @RabbitHandler
    public void payment(String message){
      log.info("api.report.payment receive message: "+ message);
    }

    @RabbitListener(queues = "api.report.refund")
    @RabbitHandler
    public void refund(String message){
        log.info("api.report.refund receive message: "+ message);
    }


}
