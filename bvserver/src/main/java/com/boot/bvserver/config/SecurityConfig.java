package com.boot.bvserver.config;

import com.boot.bvserver.controller.Urls;
import com.boot.bvserver.security.CustomUserDetailsService;
import com.boot.bvserver.security.Md5PasswordEncoder;
import com.boot.bvserver.security.SecurityAccessDeniedHandler;
import com.boot.bvserver.security.SecurityFailureHandler;
import com.boot.bvserver.security.SecurityLogoutSuccessHandler;
import com.boot.bvserver.security.SecuritySuccessHandler;
import com.boot.bvserver.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 用户密码策略设置
     *
     * @return
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    /**
     * 自定义UserDetailsService，授权
     *
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    /**
     * Security 验证通过后请求
     *
     * @return
     */
    @Bean
    public SecuritySuccessHandler securitySuccessHandler() {
        return new SecuritySuccessHandler();
    }

    /**
     * Security 验证失败后请求
     *
     * @return
     */
    @Bean
    public SecurityFailureHandler securityFailureHandler() {
        return new SecurityFailureHandler();
    }

    /**
     *Security 账号注销后调用
     *
     * @return
     */
    @Bean
    public SecurityLogoutSuccessHandler securityLogoutSuccessHandler() {
        return new SecurityLogoutSuccessHandler();
    }

    /**
     * 没有访问权限调用
     *
     * @return
     */
    @Bean
    public SecurityAccessDeniedHandler securityAccessDeniedHandler() {
        return new SecurityAccessDeniedHandler();
    }

    /**
     * SpringSecurity 面膜策略设置 用户信息查询
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        //配置静态文件不需要认证
        web.ignoring().antMatchers("/static-x/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 系统使用 token 进行校验，禁用 session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 在认证用户名之前检验 token 是否有效
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
                // 所有用户均可访问的资源
                .antMatchers(  "/page/login", "/login", "/page/403", "/get/value",
                        Urls.PAGE_CODE, "/api/messages/**", "/ws/**", "/api/users").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasAnyRole(new String [] {"ADMIN", "ROLE_COMMON_ADMIN","ROLE_USER"})
                // 除了上面请求其他请求都需要认证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username").passwordParameter("password")
                .successHandler(securitySuccessHandler()).failureHandler(securityFailureHandler())
                .and()
                //权限拒绝的页面
                .exceptionHandling().accessDeniedHandler(securityAccessDeniedHandler());
        http.logout().logoutSuccessHandler(securityLogoutSuccessHandler()).logoutSuccessUrl("/page/login");
        //http.cors().configurationSource(configurationSource()).and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 前后端分离允许跨域
        http.csrf().disable();
    }

    /**
     * @Title: configurationSource
     * @Description: 设置跨域
     * @return: org.springframework.web.cors.CorsConfigurationSource
     */
    private CorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600l);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
