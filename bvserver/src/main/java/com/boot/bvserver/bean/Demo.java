package com.boot.bvserver.bean;

import lombok.Data;

@Data
public class Demo {
    private Long id;
    private String name;
    public Demo() {
    }

    public Demo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
