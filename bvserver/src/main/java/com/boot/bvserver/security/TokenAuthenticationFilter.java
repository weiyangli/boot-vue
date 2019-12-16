package com.boot.bvserver.security;

import com.boot.bvserver.bean.Constant;
import com.boot.bvserver.bean.User;
import com.boot.bvserver.controller.Urls;
import com.boot.bvserver.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用 token 进行身份验证的过滤器。
 * 如果 header 或者 cookie 中有 auth-token，获取它对应的用户，如果用户有效则放行访问，否则 AJAX 请求返回 401 错误，普通页面重定向到登录页。
 * 如果 header 或者 cookie 没有 auth-token，则进入下一个过滤器使用表单登录验证。
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenService tokenService;

    // 身份认证 token 的有效期
    @Value("${authTokenDuration}")
    private int authTokenDuration;

    public TokenAuthenticationFilter() {
        super("/"); // 参考 UsernamePasswordAuthenticationFilter
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // 从 token 中提取 user，如果 user 不为 null，则用其创建一个 Authentication 对象
        User user = tokenService.extractUser(Utils.getAuthToken(request));

        if (user == null) {
            return null;
        } else {
            // token 不为空创建 UsernamePasswordAuthenticationToken 后面需要对 token 进行校验
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 如果 header 或者 cookie 里有 auth-token 时，则使用 token 查询用户数据进行登陆验证
        if (Utils.getAuthToken(request) != null) {
            // 1. 尝试进行身份认证
            // 2. 如果用户无效
            //    2.1. AJAX 请求返回 401，方便拦截统一处理
            //    2.2. 普通请求则删除 token，重定向到登录页
            // 3. 如果用户有效，则保存到 SecurityContext 中，供本次访问后续使用
            Authentication auth = attemptAuthentication(request, response);

            if (auth == null) {
                if (Utils.useAjax(request)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token 无效");
                } else {
                    response.sendRedirect(Urls.PAGE_LOGIN);
                }
                return;
            } else  {
                SecurityContextHolder.getContext().setAuthentication(auth);

                // 如果 header 里有 save-auth-token: true，则保存 token 到 cookie
                // 移动端使用 web view 打开页面时通过 header 设置 auth-token，通过这样的方式把 auth-token 持久化到 cookie 里，
                // 以后页面中的访问都能带上用户登录信息了
                if ("true".equals(request.getHeader("save-auth-token"))) {
                    String token = Utils.getAuthToken(request);
                    Utils.writeCookie(response, Constant.AUTH_TOKEN_KEY, token, authTokenDuration);
                }
            }
        }
        // 继续调用下一个 filter: UsernamePasswordAuthenticationToken
        chain.doFilter(request, response);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
