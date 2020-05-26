package com.liweiyang.spark.config;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.liweiyang.spark.zk.ZookeeperProperties;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    /**定时任务命名空间**/
    @Value("${stockJob.namespace}")
    private  String JOB_NAMESPACE;

    /**
     * 获取 CuratorFramework
     * 使用 curator 操作 zookeeper
     * @return
     */
    @Bean
    public CuratorFramework curatorFramework(ZookeeperProperties zookeeperProperties) {
        //配置 zookeeper 连接的重试策略(重连机制)
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zookeeperProperties.getBaseSleepTimeMs(), zookeeperProperties.getMaxRetries());

        //构建 CuratorFramework
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString(zookeeperProperties.getConnectString())
                        .sessionTimeoutMs(zookeeperProperties.getSessionTimeoutMs())
                        .connectionTimeoutMs(zookeeperProperties.getConnectionTimeoutMs())
                        .retryPolicy(retryPolicy)
                        .build();
        //连接 zookeeper
        curatorFramework.start();
        return curatorFramework;
    }

    /** zk的配置及创建注册中心**/
    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter setUpRegistryCenter(ZookeeperProperties zookeeperProperties){
        //zk的配置
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(zookeeperProperties.getConnectString(), JOB_NAMESPACE);
        //定义超时时间
        zookeeperConfiguration.setSessionTimeoutMilliseconds(1000);
        //创建注册中心
        CoordinatorRegistryCenter zookeeperRegistryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        return zookeeperRegistryCenter;
    }
}
