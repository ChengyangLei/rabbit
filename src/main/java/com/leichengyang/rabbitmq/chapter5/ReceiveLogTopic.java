package com.leichengyang.rabbitmq.chapter5;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: topic
 * @author: leichengyang
 * @create: 2022/3/18
 **/
public class ReceiveLogTopic {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Constant.TOPIC_QUEUE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = channel.queueDeclare().getQueue();

        if (args.length<1){
            System.out.println("receive log [info] [error] [warning]");
            System.exit(1);
        }

        for (String routingKey : args){
            channel.queueBind(queueName,Constant.TOPIC_QUEUE_NAME,routingKey);
        }

        System.out.println("wait message ... ");

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("receive mesage: "+delivery.getEnvelope().getRoutingKey()+" " + message );
            }
        };

        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {});



    }

}
