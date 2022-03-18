package com.leichengyang.rabbitmq.chapter4;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: direct
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class ReceiveLogDirect {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Constant.DIRECT_QUEUE_NAME, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();

        if (args.length<1){
            System.out.println("receivelog [info] [error] [warn]");
            System.exit(1);
        }

        for(String serverity:args){
            channel.queueBind(queueName,Constant.DIRECT_QUEUE_NAME,serverity);
        }

        System.out.println("wait message:...");

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("receive message:"+delivery.getEnvelope().getRoutingKey()+"  "+message);
            }
        };

        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {});


    }
}
