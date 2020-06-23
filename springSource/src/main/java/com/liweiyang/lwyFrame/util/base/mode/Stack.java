package com.liweiyang.lwyFrame.util.base.mode;

/**
 * 手写栈结构:
 * 1. 先入后出，数据只能从顶部消费
 * 2. 使用过的数据立马移除
 * 3. 往栈中放入数据
 */
public class Stack {
    private Object [] datas;
    private int MAX;
    private int top = -1;


    /**
     * 创建指定大小的栈结构
     *
     * @param num
     */
    public Stack(int num) {
        MAX = num;
        datas = new Object [num];
    }

    //栈满
    public boolean isFull() {
        return top == MAX - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 插入数据
     *
     * @param object
     */
    void push(Object object) {
        if (isFull()) {
            System.out.println("栈已满，请先消费");
            return;
        }
        top++;
        datas[top] = object;
    }

    /**
     * 使用并移除末尾数据
     *
     */
    Object pop() {
        if (isEmpty()) {
            throw new NullPointerException("栈已空");
        }
        Object result = datas[top];
        top--;
        return result;
    }

    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
     void list() {
        if(isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for(int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, datas[i]);
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);
        for (int i = 0; i < 15; i++) {
            stack.push(i + 1);
        }
        Object value =  stack.pop();
        System.out.println(value);
        stack.list();
    }
}
