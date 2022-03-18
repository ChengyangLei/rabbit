package com.leichengyang.chapter8;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * HeadersExchange交换机是根据请求消息中设置的header attribute参数类型来匹配的（和routingKey没有关系）。
 *
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@Configuration
public class HeadersConfig {

    @Bean
    public Queue creditBankQueue(){
        return new Queue("credit.bank");
    }

    @Bean
    public Queue creditFinanceQueue(){
        return new Queue("credit.finance");
    }

    @Bean
    public HeadersExchange creditBankExchange(){
        return new HeadersExchange("creditBankExchange");
    }

    @Bean
    public HeadersExchange creditFinanceExchange(){
        return new HeadersExchange("creditFinanceExchange");
    }

    /**
     * whereAll() 全部匹配
     *
     * @param creditBankQueue
     * @param creditBankExchange
     * @return
     */
    @Bean
    public Binding creditBankBinding(Queue creditBankQueue,HeadersExchange creditBankExchange){
        Map<String,Object> headers=new HashMap<>();
        headers.put("type","crash");
        headers.put("aging","fast");
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headers).match();
    }

    /**
     * whereAny()任意一个匹配
     *
     * @param creditFinanceQueue
     * @param creditFinanceExchange
     * @return
     */
    @Bean
    public Binding creditFinanceBinding(Queue creditFinanceQueue,HeadersExchange creditFinanceExchange){
        Map<String, Object> headers = new HashMap<>();
        headers.put("type","crash");
        headers.put("aging","fast");
        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headers).match();
    }


}
