package com.liweiyang.lwyFrame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPool {

    // 核心线程数(可执行的线程数量)
    private static final int corePoolSize = 8;
    // 线程池的最大数量(允许创建的最大线程数，当 corePoolSize 核心线程数都已被占用，并且阻塞队列也满了
    // 总的线程数小于该值，还可以创建线程)
    private static final int maximumPoolSize = 100;
    // 线程活动保持时间
    private static final Long keepAliveTime = 1L;

    /**
     * 线程池的运行流程
     * 一个线程被执行时，当运行的线程数小于 corePoolSize，直接创建线程并运行，
     * 大于等于则判断阻塞队列是否已满，队列没满放入队列中，队列满了，判断线程数是否大于maximumPoolSize
     * 没有大于继续创建线程，如果大于了，使用饱和测率处理线程，默认的 AbortPolicy 策略，当线程被拒绝
     * 直接抛出异常 RejectedExecutionException。 DiscardPolicy 该策略直接丢弃了多余的线程
     * @return
     */

    @Bean
    public ExecutorService autoPool() {
        return new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue(50));
    }
}
