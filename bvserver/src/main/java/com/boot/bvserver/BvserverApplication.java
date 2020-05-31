package com.boot.bvserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BvserverApplication {

    public static void main(String[] args) {
        // 防止 netty 冲突报错
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BvserverApplication.class, args);
        System.out.println("项目启动成功！");
    }

}
