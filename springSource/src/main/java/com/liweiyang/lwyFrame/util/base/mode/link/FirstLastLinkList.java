package com.liweiyang.lwyFrame.util.base.mode.link;

/**
 * 双端列表，不是双向列表
 *
 */
public class FirstLastLinkList {
    // 链表第一个链结构
    private LinkBean first;
    // 链表最后一个链结构
    private LinkBean last;

    public FirstLastLinkList() {
        first = null;
        last = null;
    }

    public void insertFirst(int data, double other) {
         LinkBean linkBean = new LinkBean(data, other);
         linkBean.setNext(first);
         // 插入的是第一个即是头又是尾
         if (isEmpty()) {
             last = linkBean;
         }
        first = linkBean;
    }

    public void insertLast(int data, double other) {
        LinkBean linkBean = new LinkBean(data, other);
        // 插入的是第一个即是头又是尾
        if (isEmpty()) {
            first = linkBean;
        } else {
            last.setNext(linkBean);
        }
        last = linkBean;
    }

    public void displayLink() {
        LinkBean current = first;
        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
    }

    public boolean isEmpty() {
        if (first == null) {
            return true;
        }
        return false;
    }

    // 双端链表测试
    public static void main(String[] args) {
        FirstLastLinkList firstLastLinkList = new FirstLastLinkList();
        for (int i = 0; i < 10; i++) {
            firstLastLinkList.insertFirst(i + 1, i + 1);
        }
        firstLastLinkList.insertLast(11, 11.5);
        firstLastLinkList.insertLast(12, 12.5);
        firstLastLinkList.displayLink();
    }
}
