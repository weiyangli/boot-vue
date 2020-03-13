package com.boot.bvserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@MapperScan("com.**.dao")  因为内部需要 spring 构建的类都用注解注入了，该注解就不用加了
@SpringBootApplication
@EnableAspectJAutoProxy
public class BvserverApplication {

    public static void main(String[] args) {
        // 防止 netty 冲突报错
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BvserverApplication.class, args);
        System.out.println("项目启动成功！");
    }

}
