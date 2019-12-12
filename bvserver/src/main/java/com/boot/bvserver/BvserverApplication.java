package com.boot.bvserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@MapperScan("com.**.dao")
@SpringBootApplication
public class BvserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BvserverApplication.class, args);
    }

}
