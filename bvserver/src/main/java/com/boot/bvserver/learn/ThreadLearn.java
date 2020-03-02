package com.boot.bvserver.learn;

/**
 * 关于线程知识的学习
 *
 * @author lwy
 */
public class ThreadLearn {

    private static int num = 50;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                ThreadLearn test = new ThreadLearn();
                test.computerd();
            }).start();
        }
    }

    public void computerd() {
        // 模拟多线程下库存扣减
        try {
            if (num == 0) {
                System.out.println("该商品已售罄。");
            } else {
                Thread.sleep(2000);
                System.out.println("恭喜你抢到了第" + num-- + "商品!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
