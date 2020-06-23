package com.liweiyang.lwyFrame.util.base.mode;

import java.util.Optional;

/**
 * 通过数组实现队列:
 * 思想：先进先出，插入数据时放到尾部，使用数据时使用头部
 *
 */
public class Queue {

    private static Object [] data;

    // 队列的最大容量
    private static int max = 0;

    // 排在队列最前面的数据下标
    private static int headIndex = -1;

    // 排在队列最后面的数据下标
    private static int endIndex = -1;

    public Queue(int size) {
        data = new Object[size];
        max = size;
    }

    // 插入数据放到队尾
    public void push(Object o) {
        // 1. 队列是否已经满了
        // 2. 将数据放在尾部
        if (!isFull()) {
           endIndex++;
           data[endIndex] = o;
           System.out.println("放入" + o.toString());
        }
    }

    // 获取对头的数据
    public Object get() {
        // 1. 队列的非空校验
        // 2. 对头下标必须小于等于对尾下标(大于的部分数据还没有插入,获取时空的没意义)
        if (!isEmpty() && headIndex < endIndex) {
            headIndex++;
            Object result = data[headIndex];
            return result;
        }
        return null;
    }

    public boolean isFull() {
        if (endIndex == max - 1) {
            System.out.println("队列已经满了");
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (data.length == 0) {
            System.out.println("队列是空的");
            return true;
        }
        return false;
    }

    /**
     * 校验是否还有可用的数据
     *
     * @return
     */
    public static int isHas() {
        return endIndex - headIndex;
    }

    public static void main(String[] args) {
        Queue q = new Queue(100);
        // 生产数据
        for (int i = 0; i < 50;  i++) {
            q.push(i + 1);
        }
        // 消费数据
        /*for (int j = 0; j < 10; j++) {
            System.out.println("获取的数据是" + Optional.ofNullable(q.get()).orElse("没有数据了").toString());
        }*/
        while (isHas() != 0 ) {
            System.out.println(isHas());
            System.out.println("获取的数据是" + Optional.ofNullable(q.get()).orElse("没有数据了").toString());
        }
    }

}
