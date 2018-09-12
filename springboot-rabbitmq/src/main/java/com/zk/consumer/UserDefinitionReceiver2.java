package com.zk.consumer;

import com.rabbitmq.client.Channel;
import java.nio.charset.Charset;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 使用@RabbitListener中的bindings属性，就不需要另外的绑定配置
 *
 * 小结：注解@RabbitListener在项目启动时会被容器发现，并创建相关的Queue和Exchange对象，如果Queue和Exchange对象不存在binding关系，则绑定
 * 若已绑定过，则无法改变绑定关系。
 */
@Component
public class UserDefinitionReceiver2 implements ChannelAwareMessageListener {

  @RabbitListener(bindings = @QueueBinding(
      value = @Queue(value = "status_change_queue_2", durable = "true"),
      exchange = @Exchange(value = "status_change_exchange_2", type = "topic"),
      key = "status.change.#"
  ))
  @Override
  public void onMessage(Message msg, Channel channel) throws Exception {
    byte[] body = msg.getBody();
    String s = new String(body, Charset.forName("utf-8"));
    System.out.println("UserDefinitionReceiver 2 , Receiver  : " + s);

  }

//  @RabbitListener(bindings = @QueueBinding(
//      value = @Queue(value = "status_change_queue_2", durable = "true"),
//      exchange = @Exchange(value = "status_change_exchange_2", type = "topic"),
//      key = "status.change.#"
//  ))
//  public void process(Message msg) {
//    byte[] body = msg.getBody();
//    String s = new String(body, Charset.forName("utf-8"));
//    System.out.println("UserDefinitionReceiver 2 , Receiver  : " + s);
//  }


}
