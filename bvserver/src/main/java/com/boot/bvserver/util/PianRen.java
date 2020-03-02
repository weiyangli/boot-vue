package com.boot.bvserver.util;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PianRen {
    /*
    * 生成一副扑克牌随机，根据斗地主模式发给三个人，地主多给三张
    * */
    public static void main(String[] args) {
        List<String> strs = getCards();
        // 打乱顺序
        Collections.shuffle(strs);
        // 输出 3 付牌
        List<String> play1 = strs.subList(0, 17);
        List<String> play2 = strs.subList(17, 34);
        List<String> play3 = strs.subList(34, 51);
        System.out.println("发牌=========================");
        System.out.println("play1 的牌:");
        System.out.println(StringUtils.join(play1, " "));
        System.out.println("play2 的牌:");
        System.out.println(StringUtils.join(play2, " "));
        System.out.println("play3 的牌:");
        System.out.println(StringUtils.join(play3, " "));
        Scanner scanner = new Scanner(System.in);
        System.out.println("谁要当地主? 请输入：p1, p2, p3");
        String [] player = new String[] {"p1", "p2", "p3"};
        if (scanner.hasNext()) {
            String str1 = scanner.next();
            if (Arrays.asList(player).contains(str1)) {
                System.out.println(str1 + "抢地主！");
                System.out.println("剩下的三张牌是:" + StringUtils.join(strs.subList(51, 54), " "));
                // 输出地主的牌
                List<String> result = new ArrayList<>();
                result = str1.equals("p1") ? play1 : (str1.equals("p2") ? play2 : play3);
                result.addAll(strs.subList(51, 54));
                System.out.println(str1 + "最终的牌是===》" +  StringUtils.join(result, " "));
            } else {
                System.out.println("请输入正确的值!");
            }
        }
    }

    // 生成一副牌
    public static List<String> getCards() {
        String [] cardTypes = new String[] { "♦", "♥",  "♠", "♣" };
        String [] points = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        List<String> cards = new ArrayList<>();
        cards.add("大王");
        cards.add("小王");
        for(int j = 0; j < cardTypes.length; j++) {
            for (int i = 0; i < points.length; i++) {
                cards.add(cardTypes[j] + points[i]);
            }
        }
        return cards;
    }
}
