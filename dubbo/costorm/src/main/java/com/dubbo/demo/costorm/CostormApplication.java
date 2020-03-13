package com.dubbo.demo.costorm;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class CostormApplication {

    public static void main(String[] args) {
        SpringApplication.run(CostormApplication.class, args);
    }
}
