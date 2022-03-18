package com.leichengyang.rabbitmq.chapter4;

import com.leichengyang.rabbitmq.Constant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @description: direct
 * @author: leichengyang
 * @create: 2022/3/17
 **/
public class EmitLogDirect {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Constant.DIRECT_QUEUE_NAME, BuiltinExchangeType.DIRECT);

        String serverity=getServerity(args);
        String message=getMessage(args);

        channel.basicPublish(Constant.DIRECT_QUEUE_NAME, serverity,null,message.getBytes());
        System.out.println("send message:"+ message);
    }

    private static String getMessage(String[] args) {
        if (args.length <2){
            return "hello world";
        }
        return joinStrings(args," ",1);
    }

    private static String getServerity(String[] args) {
        if (args.length < 1){
            return "info";
        }
        return args[0];
    }

    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0) return "";
        if (length <= startIndex) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }


}
