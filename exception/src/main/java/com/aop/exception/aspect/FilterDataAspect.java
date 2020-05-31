package com.aop.exception.aspect;


import com.alibaba.fastjson.JSONObject;
import com.aop.exception.aspect.annotation.AutoException;
import com.aop.exception.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**
 * 数据权限切面处理类
 *
 * @Date 2019年4月10日
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class FilterDataAspect{

    private final static int ERROR_CODE = 500;

    @Pointcut("@annotation(com.aop.exception.aspect.annotation.AutoException)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入日志输出环节");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AutoException syslog = method.getAnnotation(AutoException.class);
        JSONObject jsonObject = new JSONObject();
        if (syslog != null) {
            jsonObject.put("errorTitle", syslog.value()) ;
        }
        log.info("接收到的参数是 {}", jsonObject.toJSONString());
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            StackTraceElement[] stackTraceElements =  e.getStackTrace();
            StringBuffer sb = new StringBuffer();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                sb.append(stackTraceElement.toString());
            }
            jsonObject.put("status", ERROR_CODE);
            jsonObject.put("errorMessage", sb.toString());
            HttpServletResponse httpServletResponse =  WebUtil.getResponse();
            if (WebUtil.useAjax(WebUtil.getRequest())) {
                // ajax 请求
                WebUtil.ajaxResponse(httpServletResponse, jsonObject.toJSONString(), ERROR_CODE);
            } else {
                httpServletResponse.sendRedirect("/html/500.html?errorMessage=" + URLEncoder.encode(sb.toString(), "UTF-8") + "&title=" + URLEncoder.encode(syslog.value(), "UTF-8"));
            }
        }
        log.info("结束日志输出环节");
        return result;
    }

}
