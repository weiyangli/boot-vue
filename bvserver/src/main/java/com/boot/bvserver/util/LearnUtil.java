package com.boot.bvserver.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LearnUtil {

    /**
     * 静态方法中不能使用 this 关键字，
     * 因为 this 属性是指向对象的，对象实例化才能使用 this，
     * static 声明的属性、方法、类全是静态的，java 虚拟机会有
     * 一个静态区专门存储，jvm 加载类时会先给静态的区域加载到静态区，
     * 可以供其他地方直接使用，而这个时候静态区域使用 this， 那个这个
     * this 要指向那个对象呢？
     */
    private static final List<JSONObject> prizes = new ArrayList<>();

    private static String NOT_PRIZE = "谢谢惠顾";

    private static int count = 0;

    static {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Iphone 11");
        jsonObject.put("rate", 1);
        jsonObject.put("level", 1);
        prizes.add(jsonObject);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "Aj1");
        jsonObject2.put("rate", 2);
        jsonObject2.put("level", 2);
        prizes.add(jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name", "电饭锅");
        jsonObject3.put("rate", 3);
        jsonObject3.put("level", 3);
        prizes.add(jsonObject3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("name", "爱奇艺月卡");
        jsonObject4.put("rate", 4);
        jsonObject4.put("level", 4);
        prizes.add(jsonObject4);
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("name", NOT_PRIZE);
        jsonObject5.put("rate", 90);
        jsonObject5.put("level", 5);
        prizes.add(jsonObject5);
    }

    public static Map<Integer, String> createdPool() {
        Map<Integer, String> pool = new HashMap<>();
        int num = 1;
        // 根据奖品的中奖概率创建奖品池
        for (int i = 0; i < prizes.size(); i++) {
            int rate = (int)prizes.get(i).get("rate");
            String prizeName = (String)prizes.get(i).get("name");
            for (int j = 0; j < rate; j++) {
                pool.put(num++, prizeName);
            }
        }
        return pool;
    }

    private static void pickPrize() {
        // 抽奖
        Random r = new Random();
        int num = r.nextInt(100) + 1;
        System.out.println("您抽到的号码是" + num);
        // 实例化对象获得奖品信息
        String prize = createdPool().get(num);
        if (prize.equals(NOT_PRIZE)) {
            System.out.println("你离奖品只差一个身位！");
        } else {
            count += 1;
            System.out.println("恭喜你获得了" + prize);
        }
    }

    public static void learnInstanceof(Object data) {
        if (data instanceof String) {
            System.out.println("传入的是一个字符串");
        } else if (data instanceof Integer) {
            System.out.println("传入的是一个整形数字");
        } else if (data instanceof Boolean) {
            System.out.println("传入的是一个 bool");
        } else if (data instanceof Long) {
            System.out.println("传入的是一个长整形");
        }
    }

    public static void main(String[] args) throws Exception{
    }
}
