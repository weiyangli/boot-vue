package com.boot.bvserver.config;

import com.boot.bvserver.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SnowFlake Id 生产器 bean 注入
 */
@Configuration
public class SnowFlakeConfig {

    @Value("${workerId}")
    private long workerId;

    @Bean
    public IdWorker IdWorker() {
        return new IdWorker(workerId);
    }
}
