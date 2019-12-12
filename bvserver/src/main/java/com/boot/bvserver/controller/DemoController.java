package com.boot.bvserver.controller;

import com.boot.bvserver.bean.Demo;
import com.boot.bvserver.bean.Result;
import com.boot.bvserver.service.DemoService;
import com.boot.bvserver.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private IdWorker  idWorker;

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "hello bootvue";
    }

    @GetMapping("/insert")
    @ResponseBody
    public Object insertOrUpdateDemo(@RequestParam String name){
        Demo demo = new Demo(idWorker.nextId(), name);
        demoService.insertOrUpdateDemo(demo);
        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object insertOrUpdateDemo(@PathVariable Long id){
        System.out.println(id);
        return "success";
    }
}
