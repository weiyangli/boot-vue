package com.liweiyang.lwyFrame.util;

import java.util.Stack;

public class DataGroup {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("你阿红");
        stack.add("dsd");
        System.out.println(stack.firstElement());
    }
}
