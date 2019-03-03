package com.zk.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 任何发送到Direct Exchange的消息都会被转发到RouteKey中指定的Queue。
 *
 * 1.一般情况可以使用rabbitMQ自带的Exchange：”"(该Exchange的名字为空字符串，下文称其为default Exchange)。
 *
 * 2.这种模式下不需要将Exchange进行任何绑定(binding)操作
 *
 * 3.消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
 *
 * 4.如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
 *
 * @author zhukai
 */
public class RoutingSendDirect {

  private static final String EXCHANGE_NAME = "direct_logs";
  // 路由关键字
  private static final String[] routingKeys = new String[]{"info", "warning", "error"};

  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
//		声明交换器
    channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//		发送消息
    for (String severity : routingKeys) {
      String message = "Send the message level:" + severity;
      channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
      System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
    }
    channel.close();
    connection.close();
  }
}
