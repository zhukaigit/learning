package com.zk.other;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.stereotype.Service;

/**
 * 失败后回调
 */
@Service("returnCallBackListener")
public class ReturnCallBackListener implements ReturnCallback{
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.err.println("\n【ReturnCallback】====》start");
        System.err.println("MessageProperties = " + message.getMessageProperties());
        System.err.println("message body = " + new String(message.getBody()));
        System.err.println("replyCode = " + replyCode);
        System.err.println("replyText = " + replyText);
        System.err.println("exchange = " + exchange);
        System.err.println("routingKey = " + routingKey);
        System.err.println("【ReturnCallback】====》end\n");
    }
}