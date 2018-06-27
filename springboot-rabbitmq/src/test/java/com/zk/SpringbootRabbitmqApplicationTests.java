package com.zk;

import com.zk.sender.HelloSender;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

  @Autowired
  private HelloSender helloSender;

  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Before
  public void testBefore() {
    System.out.println("\n============================= before =============================\n");
  }

  @After
  public void testAfter() {
    System.out.println("\n============================= after =============================\n");
  }

  @Test
  public void contextLoads() {
    helloSender.send();
  }

  @Test
  public void testSend1() {
    String context = "time is " + new Date().toLocaleString();
    System.out.println("Sender : " + context);
    this.rabbitTemplate.convertAndSend("topic_exchange", "routing.zk", context);
  }

  @Test
  public void testSend2() {
    String context = "time is " + new Date().toLocaleString();
    System.out.println("Sender : " + context);
    this.rabbitTemplate.convertAndSend("topic_exchange", "routing.other", context);
  }

  @Test
  public void testSend3() {
    String context = "time is " + new Date().toLocaleString();
    System.out.println("Sender : " + context);
    this.rabbitTemplate.convertAndSend("topic_exchange_test", "routing.test", context);
  }

}
