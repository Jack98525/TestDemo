package org.example.domain;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer3 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址
        connectionFactory.setHost("192.168.64.128");
        //连接端口;默认为 5672
        connectionFactory.setPort(5672);
        //虚拟主机名称;默认为 /
        connectionFactory.setVirtualHost("/");
        //连接用户名；默认为guest
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("QUEUE_NAME", true, false, false, null);

        // 开启消息确认模式
        channel.confirmSelect();

        String message = "Hello, RabbitMQ!";
        channel.basicPublish("", "QUEUE_NAME", null, message.getBytes());

        // 等待确认
        if (channel.waitForConfirms()) {
            System.out.println("Message sent successfully!");
        } else {
            System.out.println("Message failed to send!");
        }

        //9. 释放资源
        channel.close();
        connection.close();


    }
}
