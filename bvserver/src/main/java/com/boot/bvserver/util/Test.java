package com.boot.bvserver.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("lolo");
        list.add("3333");
        list.add("2222");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("2222")) {
                iterator.remove();
            }
        }
    }
}
