package com.boot.bvserver.util;

import com.boot.bvserver.controller.LoginController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestUtil<T> {

    public void getCala(Class<T> clazz) {
        Method[] methods =  clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public static void main(String[] args) {
        TestUtil testUtil  = new TestUtil();
        testUtil.getCala(LoginController.class);
    }
}
