package com.liweiyang.lwyFrame.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Demo {
    private Long id;
    private String name;
    private Integer age;
    private String from;

    public Demo() {}

    public Demo(Long id, String name, Integer age, String from) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.from = from;
    }

    /**18岁**/
    public boolean getBool() {
        return this.age >= 18 ? true :false;
    }
    /**是少数名族**/
    public boolean getMz() {
        return this.from.equals("汉族") ? false : true;
    }
    /**姓李的**/
    public boolean getXs() {
        return this.name.equals("李") ? true : false;
    }
}
