package com.leichengyang.chapter6;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@Configuration
public class DirectConfig {

    @Bean
    public Queue paymentNotifyQueue(){
        return new Queue("notify.payment");
    }


}
