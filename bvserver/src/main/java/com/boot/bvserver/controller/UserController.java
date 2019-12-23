package com.boot.bvserver.controller;

import com.boot.bvserver.bean.Result;
import com.boot.bvserver.bean.User;
import com.boot.bvserver.service.UserService;
import com.boot.bvserver.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping(Urls.API_USER)
    public Result<List<User>> findUsers() {
        return Result.ok(userService.findUsers());
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    @GetMapping(Urls.API_CURRENT_USER)
    public Result<User> findCurrentUser() {
        return Result.ok(SecurityUtils.getLoginUser());
    }
}
