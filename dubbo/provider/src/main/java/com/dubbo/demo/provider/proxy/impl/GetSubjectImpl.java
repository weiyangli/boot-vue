package com.dubbo.demo.provider.proxy.impl;

import com.dubbo.demo.provider.proxy.GetSubject;

public class GetSubjectImpl implements GetSubject {
    private static StringBuilder allMessgae = new StringBuilder();
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }

    @Override
    public String pullMessage(String message) {
        allMessgae.append(message + ",");
        System.out.println(allMessgae);
        return allMessgae.toString();
    }
}
