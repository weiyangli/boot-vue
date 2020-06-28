package com.liweiyang.lwyFrame.Dao;

import com.liweiyang.lwyFrame.bean.DemoBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDao {

    List<DemoBean> findDemos();
}
