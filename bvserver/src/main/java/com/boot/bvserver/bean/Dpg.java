package com.boot.bvserver.bean;

import lombok.Data;

@Data
public class Dpg {
    private String USA;
    private String bing;

    public String getMessage() {
        return "hello";
    }
}
