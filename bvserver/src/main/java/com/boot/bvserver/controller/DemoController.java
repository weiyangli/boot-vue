package com.boot.bvserver.controller;

import com.boot.bvserver.bean.Demo;
import com.boot.bvserver.bean.Result;
import com.boot.bvserver.service.DemoService;
import com.boot.bvserver.util.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "测试接口")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private IdWorker  idWorker;

    @GetMapping("/get/value")
    @ApiOperation("获取数据")
    @ResponseBody
    public String hello() {
        return "hello bootvue";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "name", value = "名称", defaultValue = "张三", required = true)
    @GetMapping("/insert")
    @ResponseBody
    public Object insertOrUpdateDemo(@RequestParam String name){
        Demo demo = new Demo(idWorker.nextId(), name);
        demoService.insertOrUpdateDemo(demo);
        return Result.ok();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object insertOrUpdateDemo(@PathVariable Long id){
        System.out.println(id);
        return "success";
    }
}
