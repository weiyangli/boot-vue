package com.dubbo.demo.provider.feign;

import com.alibaba.demo.api.serve.ApiController;
import org.apache.dubbo.config.annotation.Service;

// 实现接口地址
@Service
public class ApiImpl implements ApiController {
    @Override
    public String sayHello(String message) {
        return "hello world===》" + message;
    }
}
