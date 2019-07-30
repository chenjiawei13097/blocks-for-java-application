package org.firefly.provider.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // 如果RabbitMQ服务器停止了，那么队列和消息就会丢失，即任务也就丢失。
            // 为了防止这种情况，需要同时声明队列和消息是持久的。
            // 声明队列是持久的，RabbitMQ重启，此队列也不丢失。
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            for (int i=0; i<20; i++) {
                String message = i+"............";

                // 声明消息是持久的。
                // 即便如此，消息仍无法保证不丢失，因为在RabbitMQ收到消息到保存到磁盘，有个短的时间窗。
                // 而且也许仅仅只保存到缓存，而非调用fsync(2)，写到磁盘。
                // 大多场景够用了，如果不行，则使用publisher confirms
                channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
