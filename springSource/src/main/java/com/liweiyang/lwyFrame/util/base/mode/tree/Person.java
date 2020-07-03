package com.liweiyang.lwyFrame.util.base.mode.tree;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class Person {

    private Long id;

    private String name;

    public static void main(String[] args) {
        List<String> strs =  Arrays.asList("1278168327293792258,1278157976493912065,1277506205115236354,1275686903726850049".split(","));
        System.out.println(Arrays.toString(strs.toArray()));
    }
}
