package com.zk;

import com.zk.producer.PublishService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.concurrent.TimeUnit.SECONDS;


public class TestConfirm {

    private PublishService publishService;

    private static String exChange = "DIRECT_EX";

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-config.xml");
        publishService = context.getBean(PublishService.class);
        System.out.println("\n================= start ==================\n");
    }

    @After
    public void after() throws InterruptedException {
        // 确保其他线程执行完毕
        SECONDS.sleep(2);
    }

    @Test
    public void test1() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test1---message:" + message);
        //exchange,queue 都正确,confirm被回调, ack=true
        publishService.send(exChange, "CONFIRM_TEST", message, new CorrelationData("unique_key_bizId_1"));
    }

    @Test
    public void test2() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test2---message:" + message);
        //exchange 错误,queue 正确,confirm被回调, ack=false
        publishService.send(exChange + "NO", "CONFIRM_TEST", message, new CorrelationData("unique_key_bizId_2"));
    }

    @Test
    public void test3() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test3---message:" + message);
        //exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
        publishService.send(exChange, "", message, new CorrelationData("unique_key_bizId_3"));
    }

    @Test
    public void test4() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test4---message:" + message);
        //exchange 错误,queue 错误,confirm被回调, ack=false
        publishService.send(exChange + "NO", "CONFIRM_TEST", message, new CorrelationData("unique_key_bizId_4"));
    }
}