package com.liweiyang.lwyFrame.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DemoBean {
    private Long id;
    private String name;
    private String desc;
    private Long userId;
    private LocalDateTime date;
}
