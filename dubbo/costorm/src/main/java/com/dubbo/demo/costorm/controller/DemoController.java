package com.dubbo.demo.costorm.controller;

import com.alibaba.demo.api.serve.ApiController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    /*
     * dubbo 提供了@Reference 注解，可替换 @Autowired 注解，用于引入远程服务
     * */
    @Reference
    ApiController demoApi;

    @GetMapping(Urls.API_DEMO)
    public String getMessage(@PathVariable(value = "message") String message) {
        return demoApi.sayHello(message);
    }
}
