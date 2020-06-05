package com.liweiyang.lwyFrame.mode.decorator;

/**
 * 被装饰者的实现类，一个食品可以同时
 * 时添加多种佐料
 *
 * @author liweiyang
 */
public class HotDogDecorator implements FoodDecorator{

    FoodBase foodBase;

    public HotDogDecorator(FoodBase foodBase) {
        System.out.println(Doing());
        this.foodBase = foodBase;
    }


    @Override
    public String getFoodName() {
        return foodBase.getFoodName() + "，添加热狗";
    }

    @Override
    public String getDescription() {
        return foodBase.getDescription() + ",热狗非狐狸肉！";
    }

    @Override
    public double cost() {
        return foodBase.cost() + 3.0;
    }

    @Override
    public String Doing() {
        return "往食品里面加热狗啦啦啦！";
    }
}
