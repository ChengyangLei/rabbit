package com.leichengyang.chapter8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @description: //TODO
 * @author: leichengyang
 * @create: 2022/3/18
 **/
@Slf4j
@Component
public class ApiCreditSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void creditBank(Map<String, Object> headers, String msg) {
        log.info("credit.bank send message: "+ msg);
        amqpTemplate.convertAndSend("creditBankExchange", "credit.bank", getMessage(headers,msg));
    }

    public void creditFinance(Map<String,Object> headers, String msg){
        log.info("credit.finance send message: "+ msg);
        amqpTemplate.convertAndSend("creditFinanceExchange","credit.finance",getMessage(headers,msg));
    }


    public Message getMessage(Map<String, Object> headers, String msg) {
        MessageProperties messageProperties = new MessageProperties();
        headers.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String s, Object o) {
                messageProperties.setHeader(s,o);
            }
        });
        return new Message(msg.getBytes(), messageProperties);
    }

}
