package com.boot.bvserver.security;

import com.boot.bvserver.bean.Result;
import com.boot.bvserver.bean.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityFailureHandler implements AuthenticationFailureHandler {

    /**
     * Security 账户验证失败后调用
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Result respBean = null;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            respBean = Result.fail("账户名或者密码输入错误!");
        } else if (e instanceof LockedException) {
            respBean = Result.fail("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            respBean = Result.fail("密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            respBean = Result.fail("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            respBean = Result.fail("账户被禁用，请联系管理员!");
        } else {
            respBean = Result.reqOkEnum(ResultEnum.LOGIN_FAILURE);
        }
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
