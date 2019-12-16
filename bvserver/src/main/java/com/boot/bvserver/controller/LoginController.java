package com.boot.bvserver.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @GetMapping(Urls.PAGE_LOGIN)
    public String login(@RequestParam(required = false, defaultValue = "0") int error) {
        if (error == 1 || error == 0) {
            return "html/login";
        }
        return "index";
    }

    @PostMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping(Urls.PAGE_ACCESS)
    @ResponseBody
    public String access() {
        return "没有权限!";
    }
}
