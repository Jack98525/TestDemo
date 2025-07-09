package org.example.domain;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer4 {
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
        channel.exchangeDeclare("EXCHANGE_NAME", "direct");

        // 设置 Return 回调
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println("Message returned: " + new String(body));
            System.out.println("Reply code: " + replyCode + ", Reply text: " + replyText);
        });
        String message = "Hello, RabbitMQ!";
        // mandatory=true，确保消息无法路由时触发 Return 回调
        channel.basicPublish("EXCHANGE_NAME", "invalid_routing_key", true, null, message.getBytes());


    }
}
