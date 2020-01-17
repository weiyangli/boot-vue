package com.boot.bvserver.util;

import org.apache.commons.lang3.StringUtils;

public class NoteBook {

    /**
     * 关于 Integer 与 int 拆箱和装箱和大小比较问题
     */
    public static void compareInteger() {
        // 在 jdk5 以前 java 中基本类型转为对应的包装类需要手动转换
        Integer a = 500;
        int b = a.intValue();
        Integer c = Integer.valueOf(b);
        // jdk5 以后实现内部自动拆装箱，源码编译时帮我们实现了上面的代码
        int d = a;

        // 大小比较
        System.out.println(a == b);   // 打印结果为 true int 和 Integer 会自动拆箱比较
        Integer e = Integer.parseInt("100");
        Integer f = Integer.parseInt("100");
        System.out.println(a == e);    // 打印结果为 true
        System.out.println(e == f);    // 打印结果为 false
        // 上面代码都为 Integer 类型但是比较结果不一样，原因是
        // Integer 在装箱时在 -128 到 127 之间的数会直接赋值，
        // 不在这个范围的值会重新在堆中创建个新的对象装，所以
        // 在比较 Integer 时就用 equals 别用 ==
        System.out.println(a.equals(e)); // 打印结果为 true
        System.out.println(e.equals(f)); // 打印结果为 false
    }
    /*
    * 数据库乐观锁可以防止数据重复提交
    * 页面可以通过提交按钮也可以防止重复提交
    * */
    public static void main(String[] args) throws Exception {
        System.out.println(Character.isWhitespace("二".charAt(0)));
        System.out.println(StringUtils.isBlank(" lol loo"));
    }

    /*
    * 学习清单(每学完一个模块写一遍博客总结)
    * 1.多线程、线程池、线程锁使用场景
    * 2.关于枚举的使用方式和枚举源码分析
    * 3.对于基本类型转换和小数类型的计算
    * 4.搞清楚字节流和字符流及相关联类的使用
    * 5.redis 分布式锁机制
    * 6.关于二叉树、平衡二叉树、红黑树原理学习，
    * 拓展到 hashmap 红黑树原理和数据库索引
    * 7.http 协议衍射到 tcp和udp等协议
    *
    * */
}