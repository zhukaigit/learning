package com.zk.servcie;

import com.zk.servcie.fallback.LocalServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：
 * <p>
 * Created by zhukai on 2018/7/15.
 */
@FeignClient(name = "LOCAL-SERVICE",
        url = "http://localhost:8080",
        fallback = LocalServiceFallback.class)
public interface LocalService {

    @RequestMapping("/feign/success")
    Object success();

    @RequestMapping("/feign/error")
    Object error();

    @PostMapping("/test/sleep/10s")
    Object sleep(@RequestParam("name") String name);


}
