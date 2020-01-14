package com.boot.bvserver.util;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestUtil{

    public void getCala(Class clazz) {
        Method[] methods =  clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public <T,K> void getNameAndValue(T name, K value){

    }

    public static List getList(List<? extends Collection<String>> list){
        // list.add(1);//会报参数不匹配的错误,编译期报错
        // list.add("hello");//会报参数不匹配的错误,编译期报错
        list.add(null);//添加成功
        return list;
    }

    public  static <T>List getList2(List<T> list){
        // list.add(1);//会报参数不匹配的错误,编译期报错
        // list.add("hello");//会报参数不匹配的错误,编译期报错
        list.add(null);//添加成功
        return list;
    }

    /**
     * 返回自定义类型的结果集
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> list(String name, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        return list;
    }
}
