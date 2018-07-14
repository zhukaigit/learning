package com.zk;

import com.zk.servcie.LocalService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableFeignClients
public class EurekaServcieApplicationTests {

	@Autowired
	private LocalService localService;

	@Before
	public void testBefore() {
		System.out.println("\r\n================= before =================\r\n");
	}
	@After
	public void testAfter() {
		System.out.println("\r\n================= after =================\r\n");
	}

	@Test
	public void success() {
		Object result = localService.success();
		System.out.println("结果：" + result);
	}

	@Test
	public void error() {
		Object result = localService.error();
		System.out.println("结果：" + result);
	}

	@Test
	public void sleep() {
		Object result = localService.sleep("zk");
		System.out.println("结果：" + result);
	}

}
