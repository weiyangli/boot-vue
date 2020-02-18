package com.boot.bvserver.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NoteBook {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static Lock lock = new ReentrantLock(true);

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
     * java 基本类型/包装类型/常用的应用类型学习
     * java 8 个基本数据类型 byte, short, int, float, long, double,  boolean, char
     * 8 个类型都有对应的包装类， 包装类都用 final 修饰，不能被继承不能被重写
     *
     * */
    public static void LearnBaseType() {
        /*
         * java 中的八个基本数据类型其中除了 boolean 和 char 以外，都继承了 Number 抽象类，该类中提供了一些类型转换的接口，
         * 各自都实现并按照各自的类型特点重写了转换的方法
         */
        // java 中基本类型小转大自动转换，大转小可以强制转换，但是可能会丢失精度。
        byte a = 127;
        short d = 1;
        short f = a; // byte 自动转换 short
        int j = f;   // short 自动转换维 int
        System.out.println(j);
        float g = j; // int 自动转换为 float
        System.out.println(g);
        float op = (float) 805.8;
        long pl = (long) op; // float 是小数所以不能直接转 long, 强制转换只保留 float 的整数部分
        System.out.println(pl);
        double lplp = pl;
        System.out.println(lplp);
        double ss = g;     // float 自动转换 double
        /*
         * java 中小数的默认类型是double,因为他是双精度类型，小数精确度高
         * 如果想将小数转 float 可以在后面加个 f,后者强制转换
         * */
        float kokok = 2.23165f;
        char b = 'b';
        String c = "c";
        byte[] de = c.getBytes();
        /*
         * java math 类型提供了 BigDecimal 类型，因为 java 中的最高精度类型是 double, 但是
         * 在实际的使用中有可能会超出这个精度，所以又提供了该类型。
         * */
        BigDecimal bigDecimalA = new BigDecimal("1234567890123456789012345678901");

    }

    /*
     * java 中处理文件流有两种，一种是字节流一种是字符流。
     * java 中字节流获取文件流使用 inputstream, 他是一个抽象类所以一般都是用
     * FileInputStream 作为字节流输入处理类，这个类操作文件是直接操作硬盘。
     * BufferedInputStream 类是将 FileInputStream 中的文件流放到内存中，是的后面的
     * 流操作的效率更高，所以推荐 BufferedInputStream 来操作输入流。效率更高，当然也可以直接
     * 使用 FileInputStream。
     * 字节流的输出类outputstream也是个抽象基类，该类提供了一些方法操作将字符串文本转为字节流，输出到硬盘中。
     * 同样 FileOutputStream 是将文件写入硬盘中
     * BufferedOutputStream 同 BufferedInputStream 一样可以提供流的操作效率
     *
     * */
    public static void IoLearn() throws Exception {
        // 1. 字节流的输入
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\lwy\\Desktop\\123.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] buff = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(buff)) != -1) {
            System.out.println(new String(buff, 0, len));
        }
        fileInputStream.close();
        bufferedInputStream.close();
        // 2. 字节流的输出
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\lwy\\Desktop\\456.txt");
        fileOutputStream.write("我是一个大帅哥!".getBytes());
        fileOutputStream.close();
    }

    /*
     * java中线程池的基类是 Executor 借口类，ExecutorService 集成了 Executor 并进行了拓展
     * AbstractExecutorService抽象类实现了 ExecutorService, ThreadPoolExecutor 继承了 AbstractExecutorService，
     * ThreadPoolExecutor 中定义了线程池的创建方法， Executors 在 ThreadPoolExecutor 的基础上进行了扩展，延伸了几种
     * 创建线程的方式，开发中可以直接使用，阿里开发手册建议不使用 Executors 类中的方法，而是自己创建线程池。
     * 线程池的优点和数据库连接池类是都是体现了空间换时间的思想，体检创建好线程供使用，提高了系统的效率，避免了不必要的线程创建
     * */
    public void threadPool() {
        // 创建一个固定线程的线程池，池中的线程都被占用时多余线程进入等待状态
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // ThreadFactory 创建线程的工厂是个借口，里面就一个创建线程的方法，可以根据自己的需求创建线程
        ThreadFactory threadFactory = (r) -> {
                return null;
        };
        Executors.newFixedThreadPool(3, threadFactory);
        // 创建一个缓存线程池，会根据当前所需要的的线程去创建线程池，底层使用心跳机制，如果线程使用完毕，会自动杀掉线程
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        // 创建一个单线程，池中只有一个线程当池中线程死掉，自动创建一个线程，永远保证池中有一个线程可以使用
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        // 创建一个固定线程数的线程池，并且定时执行线程方法
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(() -> System.out.println("hello world") , 10, TimeUnit.SECONDS);
    }

    /*
    * java中的线程锁，有两种方式实现线程同步，一种是通过 java 中的关键字 synchronized，一种是 Java.util.concurrent 包
    * 中的 lock 接口和 ReentrantLock 实现类。
    * synchronized 加锁缺陷，一个线程获取到锁，另一个线程就必须等到所释放才能使用，效率较低，synchronized 不用写代码释放锁，
    * 执行完后会自动释放锁， lock 需要手动释放锁，一般在使用 lock 后都会在 finally 代码块中释放锁，Lock可以提高多个线程进行读操作的效率，
    * 如果并发量不大的话，用谁都行，但是并发太大的话建议使用 lock。锁分为可重入性、可中断性、公平性、读写性， synchronized 是可重入锁，
    * 在一个方法中调用同一个类中两个加锁方法，获取第二个方法时不用在获取锁，避免了死锁，lock同样具备这种性质。
    * 可中断性 synchronized 获取锁后不能中断只能等待当前线程执行完毕， lock 可以。公平性 synchronized 不行，他是谁抢到了就谁执行，lock的实现类中
    * 可以达到这个特性，
    *
    * lock 比较 synchronized：
    *   synchronized 加锁后需要加锁代码块执行完返回后其他线程才能执行，lock 可以使等待时间过长的时间中断等待，可以避免服务器 cpu 的过载，
    *   lock 可以实现公平锁，给所排队，先等待的先使用锁，synchronized不可以。
    *   synchronized 不能讲读写锁分离，当 一个线程中有读有写操作时，如果只是单纯的读的话不加锁，其他的读写，写写会加锁。
    *
    * 加锁时一定要将锁加在该加的地方
    * */
    public void  threadLock() throws Exception{
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        lock.lock();
        try {
            // 进入线程等待，别的线程可以进入
            conditionA.await();
            // 需要锁住的代码块写在这里 TODO

            System.out.println("lock.lock();");
        } finally {
            lock.unlock();
            // 唤醒 conditionB
            conditionB.signal();
        }

    }

    /*
     * 数据库乐观锁可以防止数据重复提交
     * 页面可以通过提交按钮也可以防止重复提交
     * */
    public static void main(String[] args) throws Exception {
        String [] a = "a g f d s g s f d".split(" ");
        for(int i = 0; i <= a.length; i++) {
            System.out.println(i);
        }
    }

    // 为了保持票数的一致，票数要静态
    static int tick = 20;
    public static void thread2() {
        executorService.execute(() -> {
            lock.lock();
            try {
                while (tick > 0) {
                    // 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
                    if (tick > 0) {
                        System.out.println(Thread.currentThread().getName() + "卖出了第" + tick + "张票");
                        tick--;
                    } else {
                        System.out.println("票卖完了");
                    }
                }
                try {
                    Thread.sleep(1000);//休息一秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        });
    }
    /*
    * java 中的代理模式学习: java中的代理模式指，为代理的类进行扩展或者增强。可以做到在不修改目标对象的功能前提下,对目标功能扩展。
    * java 中的代理方式有 3 种 ：Cglib代理， jdk 自带的动态代理和静态代理， spring aop 使用了 Cglib代理。
    * */


    /*
    * springaop: 工作原理：在原来的接口方法上，将将业务进行可插拔的横向拓展，也就是业务增强，用的比较多的例如：给业务接口统一添加访问日志，
    * 可以写一个注解，将访问日志时的一些必填属性设置为参数，然后将切点设置为该注解，所有使用该注解的方法，都会被 springaop 给动态代理，生成一个代理类，在原方法的基础上
    * 添加上日志相关的业务。 AnnotationAwareAspectJAutoProxyCreator 类是提供了 aop 代理原生类的
    * */
    public void spingAopLearn() {

    }
}
    /*
    * 学习清单(每学完一个模块写一遍博客总结)
    *
    *
    * 1.多线程、线程池、线程锁使用场景
    * 2.关于枚举的使用方式和枚举源码分析。java 枚举是 jdk 1.5 提供的一种新类型，主要用于声明一些常量。类似咱自己写的 private final static 常量
    * 3.对于基本类型转换和小数类型的计算   byte, short, int, float, long, double,  boolean, char
    * 4.搞清楚字节流和字符流及相关联类的使用
    * 5.redis 分布式锁机制
    * 6.关于二叉树、平衡二叉树、红黑树原理学习，
    * 拓展到 hashmap 红黑树原理和数据库索引
    * 7.http 协议衍射到 tcp 和udp 等协议
    * 8.springAop 机制分析原理搞懂
    * 9.日志相关
    * */
