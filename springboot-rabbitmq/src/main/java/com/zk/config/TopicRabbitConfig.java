package com.zk.config;

import com.zk.Util.Tools;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

  public final static String QUEUE_NAME_1 = "QUEUE_NAME_1";
  public final static String QUEUE_NAME_2 = "QUEUE_NAME_2";

  @Bean
  public Queue queue1() {
    System.out.println("init method, " + Tools.getCurrentMethodName(Thread.currentThread().getStackTrace()[1]));
    return new Queue(TopicRabbitConfig.QUEUE_NAME_1);
  }

  @Bean
  public Queue queue2() {
    System.out.println("init method, " + Tools.getCurrentMethodName(Thread.currentThread().getStackTrace()[1]));
    return new Queue(TopicRabbitConfig.QUEUE_NAME_2);
  }

  @Bean
  TopicExchange exchange() {
    System.out.println("init method, " + Tools.getCurrentMethodName(Thread.currentThread().getStackTrace()[1]));
    return new TopicExchange("topic_exchange");
  }

  /**
   * 如果使用形式如：{@link com.zk.consumer.UserDefinitionReceiver}, 则不需要该配置。
   * 而像{@link com.zk.consumer.TopicWithJReceiver} 这个需要该配置
   *
   */
  @Bean
  Binding bindingExchangeMessage(Queue queue1, TopicExchange exchange) {
    System.out.println("init method, " + Tools.getCurrentMethodName(Thread.currentThread().getStackTrace()[1]));
    return BindingBuilder.bind(queue1).to(exchange).with("routing.zk");
  }

  @Bean
  Binding bindingExchangeMessages(Queue queue2, TopicExchange exchange) {
    System.out.println("init method, " + Tools.getCurrentMethodName(Thread.currentThread().getStackTrace()[1]));
    return BindingBuilder.bind(queue2).to(exchange).with("routing.#");
  }

}
