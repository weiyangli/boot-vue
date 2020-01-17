package com.boot.bvserver.service.impl;

import com.boot.bvserver.annotation.MyAnnotation;
import com.boot.bvserver.bean.Demo;
import com.boot.bvserver.bean.UploadedFile;
import com.boot.bvserver.dao.DemoDao;
import com.boot.bvserver.service.DemoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public void insertOrUpdateDemo(Demo demo){
        demoDao.insertOrUpdateDemo(demo);
    }

    @MyAnnotation(value="测试")
    @Override
    public void testAnnotation() {
        System.out.println("注解测试");
    }

    /**
     * 测试组件 pageHelper
     * @return
     */
    @Override
    public List<Demo> findDemos() {
        PageHelper.offsetPage(1, 5);
        List<UploadedFile> uploadedFiles = demoDao.findUploadedFiles();
        PageHelper.offsetPage(1, 7);
        List<Demo> demos = demoDao.findDemos();
        return null;
    }


    /**
     * 使用 @Async 注解的方法不能在同类中调用，否则 @Async 失效
     *
     * @throws Exception
     */
    @Async
    @Override
    public void asyncMethod() throws Exception{
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(2000);
        findDemos();
        System.out.println("异步执行结束");
    }

}
