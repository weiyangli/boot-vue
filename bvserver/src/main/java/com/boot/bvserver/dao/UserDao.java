package com.boot.bvserver.dao;

import com.boot.bvserver.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    User findUserByUsername(String username);

    List<User> findUsers();
}
