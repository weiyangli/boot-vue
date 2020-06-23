package com.liweiyang.lwyFrame.util.base;

/**
 * 抽象类和接口中的方法必须被子类实现
 * 抽象类中可以有普通方法
 * 抽象类中可以定义 public 全局属性， 接口中的属性和方法都必须是 final static
 *
 * @author lwy
 */
public abstract class BaseClass {

    public void sysMain() {
        System.out.println("你好");
    }

    abstract void doing();
}
