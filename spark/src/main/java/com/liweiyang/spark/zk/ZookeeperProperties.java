package com.liweiyang.spark.zk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注入 zookeeper 的配置信息
 */
@ConfigurationProperties(prefix = "zookeeper")
@Component
@Data
public class ZookeeperProperties {
    private int baseSleepTimeMs;
    private int maxRetries;
    private String connectString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}