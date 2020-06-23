package com.liweiyang.lwyFrame.util.base.mode.link;

/**
 * 链表
 *
 */
public class LinkList {

    // 链表第一个链结构
    private LinkBean first;

    public LinkList() {
        first = null;
    }

    /**
     * 新加的数据在头部，原来的 first 值必是新加的 next 值
     *
     * @param data
     */
    public void insertFirst(int data, double other) {
        // 创建新的节点数据
        LinkBean linkBean = new LinkBean(data, other);
        // 新节点的 next 就是当前的 first
        linkBean.setNext(first);
        // 插入后第一个节点更新为当前插入节点
        first = linkBean;
    }

    /**
     * 删除链表的第一个节点就是 first, 删除这个把它的 next 又拿出来作为 first,
     * 将当前删除的数据返回使用
     */
    public LinkBean deleteFirst() {
        // 先将当前 first 的 first 取出来
        if (isEmpty()) {
            System.out.println("当前链表是空的");
            return null;
        }
        LinkBean result = first;
        LinkBean linkBean = result.getNext();
        first = linkBean;
        return result;
    }

    /**
     * 显示链表中的所有数据
     *
     * @return
     */
    public void displayList() {
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

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        for (int i = 0; i < 10; i++) {
            linkList.insertFirst(i + 1, i + 1);
        }
        linkList.displayList();

        // 自身循环引用
        LinkBean linkBean = new LinkBean(1, 1.0);
        linkBean.setNext(linkBean);
    }
}
