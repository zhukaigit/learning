package com.zk.consumer;

import com.zk.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = TopicRabbitConfig.QUEUE_NAME_2)
public class TopicWithJReceiver {

  @RabbitHandler
  public void process(String msg) {
    System.out.println("topic with # , Receiver  : " + msg);
  }


}
