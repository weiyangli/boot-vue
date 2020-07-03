package com.liweiyang.lwyFrame.util.leetcode;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Objects;

/**
 * leetcode 算法题 day_01
 */
public class LeetCodeDay1 {

    /**
     * 给定一个整数数组 nums he 一个目标值 target,请你在该数组中找出和为目标值的那两个整数，
     * 并返回他们的数组下标。
     *
     * @return
     */
    public static int [] sumOfTwoNumbers(int [] arr, int target) {
        Objects.requireNonNull(arr, "数组不能为空");
        int [] result = null;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j != i && arr[i] + arr[j] == target) {
                    result = new int [] { i, j };
                    break;
                }
            }
        }
        Objects.requireNonNull(result, "未找到匹配数据");
        return result;
    }

    public static void main(String[] args) {
        System.out.println(integerInversion(256498451));
    }

    /**
     * 随机字符翻转
     *
     * 示例：
     * 输入：awrfw123
     * 输出：321wfrwa
     */
    public static String stringInversion(String str) {
        char [] data = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = data.length - 1; i >= 0; i--) {
            sb.append(data[i]);
        }
        return sb.toString();
    }

    /**
     * 随机带符号整数翻转
     *
     * 42635
     *  0 * 10 + 4 = 4;
     *  4 * 10 + 2 = 43
     *  43 * 10 + 6 = 436
     */
    public static int integerInversion(int num) {
        int divisor = 10;
        StringBuffer sb = new StringBuffer();
        while(num / divisor < 0) {
            sb.append(num % divisor);
            divisor *= 10;
        }
        return Integer.parseInt(sb.toString());
    }
}
