package com.zk.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  //队列配置
  @Bean
  public Queue Queue() {
    return new Queue("hello");
  }

}
