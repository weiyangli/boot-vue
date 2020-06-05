package com.liweiyang.lwyFrame.mode.decorator;

/**
 * 装饰者模式的基础类(本例以食物为例子)
 *
 * @author liweiyang
 */
public interface FoodBase {
    // 食物名称
    String getFoodName();
    // 食物的描述
    String getDescription();
    // 食物的价格
    double cost();
}
