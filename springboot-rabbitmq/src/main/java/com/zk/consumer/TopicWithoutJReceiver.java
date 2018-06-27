package com.zk.consumer;

import com.zk.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = TopicRabbitConfig.QUEUE_NAME_1)
public class TopicWithoutJReceiver {

  @RabbitHandler
  public void process(String msg) {
    System.out.println("topic without # , Receiver  : " + msg);
  }


}
