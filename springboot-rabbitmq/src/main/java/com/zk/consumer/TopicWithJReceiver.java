package com.zk.consumer;

import com.zk.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicWithJReceiver {

  @RabbitHandler
  @RabbitListener(queues = TopicRabbitConfig.QUEUE_NAME_2)
  public void process(String msg) {
    System.out.println("topic with # , Receiver  : " + msg);
  }


}
