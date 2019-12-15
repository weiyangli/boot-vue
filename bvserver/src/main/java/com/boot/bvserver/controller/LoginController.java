package com.boot.bvserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "0") int error) {
        if (error == 1 || error == 0) {
            return "html/login";
        }
        return "index";
    }
}
