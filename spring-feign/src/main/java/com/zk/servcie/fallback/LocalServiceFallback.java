package com.zk.servcie.fallback;

import com.zk.servcie.LocalService;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * <p>
 * Created by zhukai on 2018/7/15.
 */
@Component
public class LocalServiceFallback implements LocalService {
    @Override
    public Object success() {
        return "error";
    }

    @Override
    public Object error() {
        return "error";
    }

    @Override
    public Object sleep(String name) {
        return "error";
    }
}
