package com.boot.bvserver.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private ExecutorService executorService = Executors.newFixedThreadPool(8);
    public static void main(String[] args) throws Exception {
        System.out.println("我在处理自己的业务，需要 2 秒给爷同步执行");
        Thread.sleep(2000L);
        // 我是个配角效率慢，需要 5 秒，可以异步
        Test t = new Test();
        t.testThread();
        System.out.println("不等你们了先给老板回复!");
    }

    public void testThread() {
        executorService.execute(() -> {
            System.out.println("哈哈我咋打怪兽真好用完");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("那几个憨批还在等我玩到这里吧!");
        });
    }
}
