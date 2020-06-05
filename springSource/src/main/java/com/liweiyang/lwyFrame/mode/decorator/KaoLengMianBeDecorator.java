package com.liweiyang.lwyFrame.mode.decorator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 被装饰者，该模式的主要角色(主要食品对象)
 * 该类可以单独使用，也可以配合着装饰者使用，
 * 往食品里面添加别的作料。
 *
 * @author liweiyang
 */
@Data
public class KaoLengMianBeDecorator implements FoodBase {

    private double PRICE = 8.0;

    @Override
    public String getFoodName() {
        return "烤冷面";
    }

    @Override
    public String getDescription() {
        return "正宗东北烤冷面!";
    }

    @Override
    public double cost() {
        return PRICE;
    }

    public static void main(String[] args) {
        List<String> menusList = Arrays.asList(new String [] { "烤冷面", "手抓饼", "热狗", "煎蛋" });
        Scanner scanner = new Scanner(System.in);
        System.out.println("你要吃点啥！");
        String need = scanner.nextLine();
        if (menusList.contains(need)) {
            System.out.println("别的还需要吗？");
            FoodBase foodBase = new KaoLengMianBeDecorator();
            String need2 = scanner.nextLine();
            if (!StringUtils.isBlank(need2) && menusList.contains(need)) {
                foodBase = new HotDogDecorator(foodBase);
                foodBase = new KaoLengMianDecorator(foodBase);
            }
            System.out.println("你好，你的" + need + "做好了。");
            String money = scanner.nextLine();
            System.out.println("一共是" + foodBase.cost());
            String ask = scanner.nextLine();
            System.out.println("咱家的食品都是新鲜的放心使用，" + foodBase.getDescription());
        } else {
            System.out.println("不好意思今天没有" + need);
        }
    }
}
