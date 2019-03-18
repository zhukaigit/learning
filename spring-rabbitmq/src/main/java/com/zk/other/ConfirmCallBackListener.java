package com.zk.other;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * 确认后回调方
 * 服务器接受到消息后，回到生产者
 */
@Service("confirmCallBackListener")
public class ConfirmCallBackListener implements ConfirmCallback{
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.err.println("\n【ConfirmCallback】====》start");
        System.err.println("correlationData = " + correlationData);
        System.err.println("ack = " + ack);
        System.err.println("cause = " + cause);
        System.err.println("【ConfirmCallback】====》end\n");
    }
}
