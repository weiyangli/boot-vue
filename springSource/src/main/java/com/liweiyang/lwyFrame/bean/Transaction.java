package com.liweiyang.lwyFrame.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

    private Trader trader;
    private int year;
    private int money;

    public Transaction(Trader trader, int year, int money) {
        this.trader = trader;
        this.year = year;
        this.money = money;
    }
}
