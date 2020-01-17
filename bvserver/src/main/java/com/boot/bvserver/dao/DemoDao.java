package com.boot.bvserver.dao;

import com.boot.bvserver.bean.Demo;
import com.boot.bvserver.bean.UploadedFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDao {
    void insertOrUpdateDemo(Demo demo);

    List<Demo> findDemos();

    List<UploadedFile> findUploadedFiles();
}
