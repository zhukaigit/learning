package com.zk.consumer;

import com.zk.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 使用@RabbitListener中的bindings属性，就不需要另外的绑定配置
 *
 * 小结：注解@RabbitListener在项目启动时会被容器发现，并创建相关的Queue和Exchange对象，如果Queue和Exchange对象不存在binding关系，则绑定
 * 若已绑定过，则无法改变绑定关系。
 */
@Component
@RabbitListener(bindings = @QueueBinding(
    value = @Queue(value = TopicRabbitConfig.QUEUE_NAME_1, durable = "true"),
    exchange = @Exchange(value = "topic_exchange", type = "topic"),
    key = "routing.#"
))
public class UserDefinitionReceiver {

  @RabbitHandler
  public void process(String msg) {
    System.out.println("UserDefinitionReceiver , Receiver  : " + msg);
  }


}
