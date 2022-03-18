package com.leichengyang.chapter9;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FanoutExchange交换机是转发消息到所有绑定队列（广播模式，和routingKey没有关系）。
 *
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@Configuration
public class FanoutConfig {

    @Bean
    public Queue reportPaymentQueue(){
        return new Queue("api.report.payment");
    }

    /**
     * 退款
     * @return
     */
    @Bean
    public Queue reportRefundQueue(){
        return new Queue("api.report.refund");
    }

    @Bean
    public FanoutExchange reportExchange(){
        return new FanoutExchange("reportExchange");
    }

    @Bean
    public Binding bindingReportPaymentBinding(Queue reportPaymentQueue,FanoutExchange reportExchange){
        return BindingBuilder.bind(reportPaymentQueue).to(reportExchange);
    }

    @Bean
    public Binding bindingReportRefundBinding(Queue reportRefundQueue,FanoutExchange reportExchange){
        return BindingBuilder.bind(reportRefundQueue).to(reportExchange);
    }





}
