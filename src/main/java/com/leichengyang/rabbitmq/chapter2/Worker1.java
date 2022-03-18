package com.leichengyang.rabbitmq.chapter2;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @description: worker queue
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class Worker1 {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(Constant.NEW_TASK_QUEUE_NAME, true, false, false, null);

            System.out.println(" wait message ...");

            channel.basicQos(1);

            DeliverCallback deliverCallback = new DeliverCallback() {
                @Override
                public void handle(String consumerTag, Delivery delivery) throws IOException {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println("receive message :" + message);

                    try {
                        doWork(message);
                    } finally {
                        System.out.println("message work done");
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

                    }
                }
            };

            channel.basicConsume(Constant.NEW_TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {});

    }

    private static void doWork(String message) {
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
