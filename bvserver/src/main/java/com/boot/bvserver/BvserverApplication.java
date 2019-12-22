package com.boot.bvserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.**.dao")
@SpringBootApplication
public class BvserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BvserverApplication.class, args);
        System.out.println("项目启动成功！");
    }

}
