package com.liweiyang.lwyFrame.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * HandlerExceptionResolver 和 @ControllerAdvice 一起使用 前者先执行
 */
@ControllerAdvice
@Slf4j
public class FrameException {

    /**
     * 处理所有继承 exception 的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public void customException(Exception e) throws Exception {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.sendRedirect("/html/500.html");
    }

    /**
     * 定义全局属性，所有 springmvc 控制层接口都可以访问
     *
     * @return
     */
    @ModelAttribute(value = "md")
    public Map<String,Object> myData() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }
}
