package com.boot.bvserver.learn;

/**
 * 关于线程知识的学习
 *
 * @author lwy
 */
public class ThreadLearn {

    private static int num = 1;

    public static void main(String[] args) throws InterruptedException {
        ThreadLearn test = new ThreadLearn();
        new Thread(() -> {
            while (true) {
                test.computerd();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                test.computerd();
            }
        }).start();
    }

    public void computerd() {
        synchronized(ThreadLearn.class) {
            // 模拟多线程下库存扣减
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("恭喜你抢到了第" + num++ + "商品!");
        }
    }
}
