package com.boot.bvserver.learn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于线程知识的学习
 *
 * @author lwy
 */
public class ThreadLearn {

    private static int num = 50;
    private static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        ThreadLearn threadLearn = new ThreadLearn();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLearn.computerd();
                }
            }).start();
        }
    }

    public void computerd() {
        // 模拟多线程下库存扣减
        lock.lock();
        try {
            if (num > 0) {
                System.out.println("恭喜你抢到了第" + num-- + "商品!");
            } else {
                System.out.println("该商品已售罄。");
            }
        } finally {
            lock.unlock();
        }
    }
}
