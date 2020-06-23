package com.liweiyang.lwyFrame.util.design;


import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

public class test {

    private static List<Long> nums = new ArrayList<>();
    private static Lock lock = new ReentrantLock(true);
    // 通过计算线程的个数，放行线程后的代码
    static CountDownLatch countDownLatch = new CountDownLatch(1000);
    private static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    @Autowired
    private static ExecutorService autoPool;

    public static void main2(String[] args) {
        Base<String, Integer>  base = Integer::new;
        Integer a = base.changeModel("1");
        String b = "f,d,f,s,d";
        List<String> ads = Arrays.asList(b.split(",")).stream().distinct().collect(Collectors.toList());
        Function<String [], String>  mapper = new Function<String[], String>() {
            @Override
            public String apply(String[] strings) {
                return StringUtils.join(Arrays.stream(strings).distinct().collect(Collectors.toList()), ",");
            }
        };
        List<String> stringStream = Optional.ofNullable(b).map(x -> x.split(",")).flatMap(x -> Optional.ofNullable(Arrays.stream(x))).get().distinct().collect(Collectors.toList());
        System.out.println(Arrays.toString(stringStream.toArray()));
    }

    /**
     * 获取中将号码
     */
    public static Long genNum() {
        Long result = null;
        lock.lock();
        try {
            result = RandomUtils.nextLong(000000, 999999);
            if (nums.contains(result)) {
                genNum();
            } else {
                nums.add(result);
            }
        } finally {
            lock.unlock();
        }
        return result;
    }

    public static void takeANum() {
        executorService.execute(() -> {
            System.out.println(Thread.currentThread() + "-->" + genNum());
            countDownLatch.countDown();
            System.out.println(countDownLatch.getCount());
        });
    }

    public static void main(String[] args) throws  Exception{
        System.out.println("本期彩票开始抽取");
        // 一万人参与
        for (int i = 0; i < 1000; i++) {
            takeANum();
        }
        countDownLatch.await();
        // 打乱号码
        Collections.shuffle(nums);
        // 从所有号码里随机取一个
        System.out.println(nums.stream().distinct().count());
        Long luck = nums.get(RandomUtils.nextInt(0, nums.size() - 1));
        System.out.println("本期彩票中奖号码是" + luck);
    }

}
