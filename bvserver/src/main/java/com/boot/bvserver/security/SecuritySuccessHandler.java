package com.boot.bvserver.security;

import com.boot.bvserver.bean.Constant;
import com.boot.bvserver.bean.Result;
import com.boot.bvserver.bean.ResultEnum;
import com.boot.bvserver.bean.Role;
import com.boot.bvserver.bean.User;
import com.boot.bvserver.dao.UserDao;
import com.boot.bvserver.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

    @Value("${authTokenDuration}")
    private int authTokenDuration; // 身份认证 token 的有效期，单位为秒

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    /**
     * Security 验证成功后调用
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 1. 查询用户信息
        // 2. 将 token 写入 response
        // 3. 跳转到 index 页面

        // [1] 查询用户信息
        String username = request.getParameter("username");
        User user = userDao.findUserByUsername(username);
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setCode("ROLE_admin").setName("管理员").setId(1l);
        roles.add(role);
        user.setRoles(roles);
        Authentication auth =  new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // [2] 将 token 写入 response
        String token = tokenService.generateToken(user);
        Utils.writeCookie(response, Constant.AUTH_TOKEN_KEY, token, authTokenDuration);
//        response.setContentType("application/json;charset=utf-8");
//        Result respBean = Result.ok(ResultEnum.LOGIN_SUCCESS, token);
//        ObjectMapper om = new ObjectMapper();
//        PrintWriter out = response.getWriter();
//        out.write(om.writeValueAsString(respBean));
//        out.flush();
//        out.close();

        // [3] 跳转到 index 页面
        request.getRequestDispatcher("/index").forward(request, response);
    }
}
