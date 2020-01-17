package com.boot.bvserver.service;

import com.boot.bvserver.bean.Demo;

import java.util.List;

public interface DemoService {
    void insertOrUpdateDemo(Demo demo);

    void testAnnotation();

    List<Demo> findDemos();

    void asyncMethod() throws Exception;
}
