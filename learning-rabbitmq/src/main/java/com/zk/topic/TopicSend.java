package com.zk.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上
 *
 * 1.这种模式较为复杂，简单来说，就是每个队列都有其关心的主题，所有的消息都带有一个“标题”(RouteKey)，Exchange会将消息转发到所有关注主题能与RouteKey模糊匹配的队列。
 *
 * 2.这种模式需要RouteKey，也许要提前绑定Exchange与Queue。
 *
 * 3.在进行绑定时，要提供一个该队列关心的主题，如“#.log.#”表示该队列关心所有涉及log的消息(一个RouteKey为”MQ.log.error”的消息会被转发到该队列)。
 *
 * 4.“#”表示0个或若干个关键字，“*”表示一个关键字。如“log.*”能与“log.warn”匹配，无法与“log.warn.timeout”匹配；但是“log.#”能与上述两者匹配。
 *
 * 5.同样，如果Exchange没有发现能够与RouteKey匹配的Queue，则会抛弃此消息。
 */
public class TopicSend {

  private static final String EXCHANGE_NAME = "topic_logs";

  public static void main(String[] argv) {
    Connection connection = null;
    Channel channel = null;
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");

      connection = factory.newConnection();
      channel = connection.createChannel();
//			声明一个匹配模式的交换器
      channel.exchangeDeclare(EXCHANGE_NAME, "topic");

      // 待发送的消息
      String[] routingKeys = new String[]{"quick.orange.rabbit",
          "lazy.orange.elephant",
          "quick.orange.fox",
          "lazy.brown.fox",
          "quick.brown.fox",
          "quick.orange.male.rabbit",
          "lazy.orange.male.rabbit"};
//			发送消息
      for (String severity : routingKeys) {
        String message = "From " + severity + " routingKey' s message!";
        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
        System.out.println("TopicSend [x] Sent '" + severity + "':'" + message + "'");
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception ignore) {
        }
      }
    }
  }
}