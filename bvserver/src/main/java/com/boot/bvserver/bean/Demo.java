package com.boot.bvserver.bean;

import lombok.Data;

@Data
public class Demo implements Comparable<Demo> {
    private Long id;
    private String name;
    public Demo() {
    }

    public Demo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Demo o) {
        return 0;
    }
}
