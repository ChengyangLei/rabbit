package com.leichengyang.rabbitmq.chapter1;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @description: hello world
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class Receive1 {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
        System.out.println(" wait message ....................");

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" receive message:   " + message);
            }
        };

        channel.basicConsume(Constant.QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

    }
}
