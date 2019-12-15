package com.boot.bvserver.dao;

import com.boot.bvserver.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User findUserByUsername(String username);
}
