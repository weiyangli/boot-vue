package com.boot.bvserver.security;

import com.boot.bvserver.bean.Role;
import com.boot.bvserver.bean.User;
import com.boot.bvserver.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.*;

/**
 *  通过用户输入用户名查询用户信息，用于和用户键入的用户信息比对
 *
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userDao.findUserByUsername(username);
         List<Role> roles = new ArrayList<>();
         Role role = new Role();
         role.setCode("admin").setName("管理员").setId(1l);
         roles.add(role);
         user.setRoles(roles);
         return user;
    }
}
