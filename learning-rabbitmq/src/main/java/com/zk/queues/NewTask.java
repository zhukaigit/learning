package com.zk.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 多个消费者监听同一个queue，各个消费者分担不同的消息，加速进度。
 * 总之，一个queue里的同一个消息只会发送给一个消费者，除非消费者消费失败，或者没有ack
 * @author zhukai
 */
public class NewTask {

  private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
//      分发消息
    for (int i = 0; i < 5; i++) {
      String message = "Hello World! " + i;
      channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
          message.getBytes());
      System.out.println(" [x] Sent '" + message + "'");
    }
    channel.close();
    connection.close();
  }
}
