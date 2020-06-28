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
         first = linkBean;
         // 插入的是第一个设置 last 节点
         if (isEmpty()) {
             last = linkBean;
         }
    }

    public boolean isEmpty() {
        if (first == null) {
            return true;
        }
        return false;
    }
}
