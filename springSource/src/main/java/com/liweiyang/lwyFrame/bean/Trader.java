package com.liweiyang.lwyFrame.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trader {

    private String name;
    private String address;

    public Trader(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
