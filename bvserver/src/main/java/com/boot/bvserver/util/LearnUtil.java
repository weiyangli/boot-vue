package com.boot.bvserver.util;

public class LearnUtil {
    /**
     * 静态方法中不能使用 this 关键字，
     * 因为 this 属性是指向对象的，对象实例化才能使用 this，
     * static 声明的属性、方法、类全是静态的，java 虚拟机会有
     * 一个静态区专门存储，jvm 加载类时会先给静态的区域加载到静态区，
     * 可以供其他地方直接使用，而这个时候静态区域使用 this， 那个这个
     * this 要指向那个对象呢？
     */
}
