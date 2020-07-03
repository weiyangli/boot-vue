package com.liweiyang.lwyFrame.util.base.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Suanfa {

    static int [] arr = new int[] {1, 2, 3, 6, 9, 15, 19, 23, 35, 39, 42, 45, 49, 52, 53, 57, 59, 60, 68, 70,
    75, 78, 80, 82, 86, 91, 96, 99 };
    static int target = 78;
    static String MIN = "MIN";
    static String RIGHT = "RIGHT";
    static String MAX = "MAX";
    static int lastNum = 0;
    static int maxNum = arr[arr.length - 1];
    static int minNum = arr[0];

    /**
     * 二分法测试
     *
     * @param value
     * @return
     */
    public static void erfenfa(int value) {
        System.out.println("当前猜的数字是" + value);
        if (value == target) {
            System.out.println("猜对了正确数字是" + value);
        } else {
            if (value > target) {
                maxNum = value;
                value = (minNum + value) / 2;
            } else {
                minNum = value;
                value = (value + maxNum) / 2;
            }
            erfenfa(value);
        }
    }

    public static void main(String[] args) {
       System.out.println(Arrays.toString(insertSort(new int [] {5, 7,3, 1, 15, 142, 0, 8, 4, 2, 52, 15, 13})));
    }

    /**
     * 冒泡排序:
     * 从头开始依次两两比较，右边比左边大交换位置，第一次比较 n - 1 次， 第二次 n - 2 次,
     * 没比较的总次数等于 (n - 1) + (n -2) + (n - 3) ... +  1 = N * (N - 1) / 2
     * @param arr
     * @return
     */
    public static int [] mpSort(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 比较次数，每一次比较后后面的数据就不参与比较了，在（arr.length - 1）累计减一
            for (int j = 0; j < arr.length - 1 - i ; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序:
     * 数据两两比较取记录最小值的下标，依次比下去。比到最后的值就是当前
     * 比较数据中的最小值，将值一一赋给左侧。比冒泡排序快一点，不用再比较的时候重复
     * 替换值
     *
     */
    public static int [] chooseSort(int [] arr) {
        // 互相比较记录最小值，全部比较完毕后，将最小的和前面的进行交换
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 依次比较得到最小的值，记录最小值的下标
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            // 每一轮比较结束，将最小值的下标和左边此次循环的起始索引替换值
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

    /**
     * 插入排序
     *
     * @return
     */
    public static int [] insertSort(int[] arr)
    {
        int i, j;
        int n = arr.length;
        int target;

        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 - n-1
        for (i = 1; i < n; i++)
        {
            j = i;
            target = arr[i];

            while (j > 0 && target < arr[j - 1])
            {
                arr[j] = arr[j - 1];
                j--;
            }

            arr[j] = target;
        }
        return arr;
    }

    /*
     * 有序数组和无序数组：
     *  有序数组查询速度要快于无序数组，根据排列好的序号按照指定算法比如二分法，可以很快查询出结果，
     *  但是有序的插入要慢于无序的数组，需要维护数据摆放的顺序。
     *  他们两者删除都很慢，因为需要在删除后将位于后面的数的顺序移动，
     */
}
