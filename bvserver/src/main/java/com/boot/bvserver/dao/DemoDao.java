package com.boot.bvserver.dao;

import com.boot.bvserver.bean.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoDao {
    void insertOrUpdateDemo(Demo demo);
}
