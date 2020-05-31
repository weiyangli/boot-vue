package com.liweiyang.spark.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

@Component
public class DemoJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("作业分片："+shardingContext.getShardingItem());
    }
}
