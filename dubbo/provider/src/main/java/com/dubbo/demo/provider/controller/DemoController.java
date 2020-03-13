package com.dubbo.demo.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/api/get/data")
    @ResponseBody
    public List<String> getData() {
        return Arrays.asList(new String [] {"你好", "很好"});
    }
}
