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

    /**
     * 指定 key 查询链表
     *
     * @param data
     * @return
     */
    public LinkBean find(int data) {
        // 从 first 比较 data 值，不相等取当前的 bean 中的 next 继续查找，
        // 直到找到目标数据，如果查到 first 等于 null 也就是链表的最后一个节点
        // 那就是链表中不包含这个值

        LinkBean current = first;
        while (current.getData() != data) {
            if (current.getNext() == null) {
                System.out.println("链表中找不到目标对象");
                return null;
            }  else {
                current = current.getNext();
            }
        }
        return current;
    }

    /**
     * 根据 data 删除指定的节点数据
     * @param data
     * @return
     */
    public LinkBean delete(int data) {
        LinkBean current = first;
        LinkBean pervious = first;
        // 1. 循环查找待删除的数据
        while(current.getData() != data) {
            if (current.getNext() == null) {
                System.out.println("没有找到待删除的数据");
                return null;
            } else {
                pervious = current;
                current = current.getNext();
            }
        }
        // 2. 找到了数据
        // 2.1 这个数据就是第一个节点，删除第一个节点，把第一节点的next赋值给 first
        if (first == current) {
            first = current.getNext();
        } else {
            // 2.2 删除的不是第一节点，那就把删除节点的上一个节点和后一个节点连接起来
            pervious.setNext(current.getNext());
        }
        return current;
    }

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        for (int i = 0; i < 10; i++) {
            linkList.insertFirst(i + 1, i + 1);
        }
        // 自身循环引用
        LinkBean linkBean = new LinkBean(1, 1.0);
        linkBean.setNext(linkBean);
        linkList.displayList();
    }
}
