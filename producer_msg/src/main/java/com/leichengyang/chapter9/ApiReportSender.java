package com.leichengyang.chapter9;

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
public class ApiReportSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * 证明了routingKey跟消费者定义的不一样，消费者一样可以接收到
     * @param msg
     */
    public void generateReport(String msg){
      log.info("api.generate.reports send message: "+ msg);
      amqpTemplate.convertAndSend("reportExchange","api.generate.reports",msg);
    }


}
