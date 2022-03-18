package com.leichengyang.chapter7;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * TopicExchange是按规则转发消息，是交换机中最灵活的一个。也是最常用的一个。
 *
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@Configuration
public class TopicConfig {

    @Bean
    public Queue coreQueue(){
        return new Queue("api.core");
    }

    @Bean
    public Queue paymentQueue(){
        return new Queue("api.payment");
    }

    @Bean
    public TopicExchange coreTopicExchange(){
        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentTopicExchange(){
        return new TopicExchange("paymentExchange");
    }

    /**
     * routingKey 配置的是*，只能向后匹配一层
     *
     * @param coreQueue
     * @param coreTopicExchange
     * @return
     */
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue,TopicExchange coreTopicExchange){
        return BindingBuilder.bind(coreQueue).to(coreTopicExchange).with("api.core.*");
    }

    /**
     * routingKey 配置的是#，可以向后匹配多层路径
     *
     * @param paymentQueue
     * @param paymentTopicExchange
     * @return
     */
    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue,TopicExchange paymentTopicExchange){
        return BindingBuilder.bind(paymentQueue).to(paymentTopicExchange).with("api.payment.#");
    }


}
