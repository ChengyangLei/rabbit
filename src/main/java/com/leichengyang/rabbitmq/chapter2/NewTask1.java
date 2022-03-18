package com.leichengyang.rabbitmq.chapter2;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: worker queue
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class NewTask1 {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Constant.NEW_TASK_QUEUE_NAME, true, false, false, null);

        String message = String.join(" ", args);
        channel.basicPublish("", Constant.NEW_TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));

        System.out.println("send message   " + message);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

    }
}
