package com.boot.bvserver.service;

import com.boot.bvserver.bean.User;

import java.util.List;

public interface UserService {

    List<User> findUsers();
}
