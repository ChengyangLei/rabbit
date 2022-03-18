package com.leichengyang.rabbitmq.chapter3;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: publisher subcribe    Sending messages to many consumers at once
 *                  发布、订阅，一次性发送消息给多个消费者
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class EmitLog {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Constant.PUBLISHER_QUEUE_NAME,"fanout");

        String message=args.length <1 ? "info: hello world" : String.join(" ",args);

        channel.basicPublish(Constant.PUBLISHER_QUEUE_NAME,"",null,message.getBytes());
        System.out.println("send message :"+message);

    }
}
