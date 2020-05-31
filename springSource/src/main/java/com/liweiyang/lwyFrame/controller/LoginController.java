package com.liweiyang.lwyFrame.controller;

import com.liweiyang.lwyFrame.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/")
    public String login() {
        return "index";
    }

    @RequestMapping("/test")
    public ModelAndView test() {
        demoService.login();
        int a = 1 / 0;
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
