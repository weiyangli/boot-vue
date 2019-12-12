package com.boot.bvserver.service.impl;

import com.boot.bvserver.bean.Demo;
import com.boot.bvserver.dao.DemoDao;
import com.boot.bvserver.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    public void insertOrUpdateDemo(Demo demo){
        demoDao.insertOrUpdateDemo(demo);
    }
}
