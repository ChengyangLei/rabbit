package com.leichengyang.rabbitmq.chapter1;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: hello world
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class Send1 {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
        String message = "this is first message";
        channel.basicPublish("", Constant.QUEUE_NAME, null, message.getBytes());
        System.out.println("send message:" + message);

    }
}
