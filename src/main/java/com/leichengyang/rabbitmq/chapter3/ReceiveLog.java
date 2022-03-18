package com.leichengyang.rabbitmq.chapter3;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: publisher subcribe
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class ReceiveLog {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Constant.PUBLISHER_QUEUE_NAME,"fanout");

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,Constant.PUBLISHER_QUEUE_NAME,"");

        System.out.println("wait message...");

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("receive message:"+ message);
            }
        };

        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {});

    }
}
