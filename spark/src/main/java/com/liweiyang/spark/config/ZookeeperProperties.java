package com.liweiyang.spark.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 注入 zookeeper 的配置信息
 */
@ConfigurationProperties(prefix = "zookeeper")
@Data
public class ZookeeperProperties {
    private int baseSleepTimeMs;
    private int maxRetries;
    private String connectString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}