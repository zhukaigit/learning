package com.zk.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishService {

    @Autowired
    private RabbitTemplate amqpTemplate;

    public void send(String exchange, String routingKey, Object message, CorrelationData correlationData) {
        amqpTemplate.convertAndSend(exchange, routingKey, message, correlationData);
    }
}
