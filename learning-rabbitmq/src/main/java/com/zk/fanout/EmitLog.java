package com.zk.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上。
 *
 * 1.可以理解为路由表的模式
 *
 * 2.这种模式不需要RouteKey
 *
 * 3.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定。
 *
 * 4.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃
 */
public class EmitLog {

  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

//      分发消息
    for (int i = 0; i < 5; i++) {
      String message = "Hello World! " + i;
      channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
      System.out.println(" [x] Sent '" + message + "'");
    }
    channel.close();
    connection.close();
  }
}
