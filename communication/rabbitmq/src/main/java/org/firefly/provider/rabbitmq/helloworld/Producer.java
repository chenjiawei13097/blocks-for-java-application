package org.firefly.provider.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Producer {
    private static final String QUEUE_NAME = "MyFirstQueue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 通过名称或IP指定要连接的RabbitMQ服务器。
        connectionFactory.setHost("localhost");
        // 默认连接端口5672。
        connectionFactory.setPort(5672);
        // 默认消息发往服务器的虚拟机"/"。
        connectionFactory.setVirtualHost("/");
        // 设置登录服务器的账户和密码。
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        try (
            // 创建到服务器的连接，它封装了Socket连接、协议版本协商、身份认证等。
            Connection connection = connectionFactory.newConnection();
            // 与RabbitMQ打交道的大多数API都在通道对象上。
            Channel channel = connection.createChannel()) {
            // 声明一个队列的操作是幂等的，只有此名称的队列不存在时才创建。
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 发送的消息是字节数组，所以可以发送任意类型的消息。
            channel.basicPublish(
                "", QUEUE_NAME, null, "Hello World!".getBytes(StandardCharsets.UTF_8));
            System.out.println("Sent '" + "Hello World!" + "'");
        }
    }
}
