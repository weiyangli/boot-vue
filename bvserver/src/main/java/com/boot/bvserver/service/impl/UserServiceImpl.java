package com.boot.bvserver.service.impl;

import com.boot.bvserver.bean.User;
import com.boot.bvserver.dao.UserDao;
import com.boot.bvserver.service.UserService;
import com.boot.bvserver.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /***
     * 查询除了自己意外的用户
     *
     * @return
     */
    @Override
    public List<User> findUsers() {
        return userDao.findUsers(SecurityUtils.getLoginUser().getId());
    }
}
