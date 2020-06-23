package com.liweiyang.lwyFrame.util.base.mode.link;

import lombok.Data;

@Data
public class LinkBean {

    private int data;

    private double other;

    private LinkBean next;

    public LinkBean (int data, double other) {
        this.data = data;
        this.other = other;
    }

    public void displayLink() {
        System.out.println("{" + data + ',' + other + "}");
    }

    public static void main(String[] args) {
        LinkBean linkBean = new LinkBean(1, 1.0);
        linkBean.displayLink();
    }
}
