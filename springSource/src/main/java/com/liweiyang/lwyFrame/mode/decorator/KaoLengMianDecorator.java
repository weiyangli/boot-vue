package com.liweiyang.lwyFrame.mode.decorator;

/**
 * 被装饰者的实现类，一个食品可以同时
 * 时添加多种佐料
 *
 * @author liweiyang
 */
public class KaoLengMianDecorator implements FoodDecorator{

    FoodBase foodBase;

    public KaoLengMianDecorator(FoodBase foodBase) {
        System.out.println(Doing());
        this.foodBase = foodBase;
    }

    @Override
    public String getFoodName() {
        return foodBase.getFoodName() + ",加鸡蛋";
    }

    @Override
    public String getDescription() {
        return foodBase.getDescription() + ",正大新鲜鸡蛋！";
    }

    @Override
    public double cost() {
        return foodBase.cost() + 1.5;
    }

    @Override
    public String Doing() {
        return "往食品里面加鸡蛋啦啦啦！";
    }
}
