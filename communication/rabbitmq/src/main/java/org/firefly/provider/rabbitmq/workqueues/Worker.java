package org.firefly.provider.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Worker {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        // RabbitMQ队列一旦创建了，它的属性参数就不能再改变，任何进程试图修改为不一样的属性都会得到一个错误。
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 解决轮询调度平均分配，但各个worker执行速度不一的问题。
        // 告诉RabbitMQ，一次分配给一个worker的消息数之多1个。
        // 即只要worker没有处理完并发送ack就不分配新的message，只会分配任务给不忙的worker。
        channel.basicQos(1);

        channel.basicConsume(
            TASK_QUEUE_NAME,
            // 即使程序通过Ctrl+C被杀掉，它的任务未处理完，
            // 但因为ack没有手动返回，所以这些没有对应ack的消息会重新分发。
            false,
            (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } finally {
                    System.out.println(" [x] Done");
                    // 向RabbitMQ发生ack，且必须在接收消息的这个channel上发生，否则产生异常。
                    // 返回ack很重要，如果忘记，那么大量的消息将在RabbitMQ上累积，消耗掉内存。
                    // rabbitmqctl.bat list_queues name messages_ready messages_unacknowledged
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            },
            consumerTag -> {
            });
    }

    // 模拟执行耗时任务，如这里字符串task中有几个点号就睡眠几秒来模拟
    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}