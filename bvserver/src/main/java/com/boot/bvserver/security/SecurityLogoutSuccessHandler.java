package com.boot.bvserver.security;

import com.boot.bvserver.bean.Constant;
import com.boot.bvserver.bean.Result;
import com.boot.bvserver.bean.ResultEnum;
import com.boot.bvserver.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Utils.deleteCookie(response, Constant.AUTH_TOKEN_KEY);
        response.setContentType("application/json;charset=utf-8");
        Result respBean = Result.ok(ResultEnum.LOGOUT_SUCCESS, "");
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
//        request.getRequestDispatcher("/logout").forward(request, response);
    }
}
