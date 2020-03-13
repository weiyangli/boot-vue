package com.dubbo.demo.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    /*
    * 使用 zuul 组件实现网关方向代理
    *
    * */
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
