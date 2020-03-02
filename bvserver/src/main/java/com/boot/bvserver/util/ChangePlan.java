package com.boot.bvserver.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class ChangePlan {

    private static final String MIMA =  "12345678";

    private static final int MIN_NUM = 8;

    private static final int MAX_NUM = 69;

    // 创建一个 wifi 中可能存在的字节数组
    private static final char [] words = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '*', '$', '%', '@', '-', '+', '!' };
    private static final char [] sampleWords = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private static List<String> errorWords = new ArrayList<>();

    public static void wifiPojie() {
        long begin = System.currentTimeMillis();
        System.out.println();
        // 密码长度 8 到 16 位,根据这个规则生成密码串和正确密码比对
        for (int i = 0; i < Math.pow(MAX_NUM, MIN_NUM); i++) {
            String genWord = checkWord();
            System.out.println("当前生成的密码是===>" +  genWord);
            if (genWord.equals(MIMA)) {
                int time = (int)(System.currentTimeMillis() - begin) / 1000 * 60 ;
                System.out.println("密码比对成功,WIFI 已解锁!此次密码比对耗时===》" + time);
                break;
            }
        }
    }

    /**
     * 生成没有比对过的串
     *
     * @return
     */
    public static String checkWord() {
        String genWord = RandomStringUtils.random(MIN_NUM, sampleWords);
        if (errorWords.contains(genWord)) {
            checkWord();
        } else {
            errorWords.add(genWord);
        }
        return genWord;
    }

    public static void main(String[] args) {
        wifiPojie();
    }
}
