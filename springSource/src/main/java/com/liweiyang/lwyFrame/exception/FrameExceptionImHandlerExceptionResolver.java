package com.liweiyang.lwyFrame.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
//@Component  不使用该方式处理异常
public class FrameExceptionImHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // 针对自己的项目需求处理接口的异常，可以自定义跳转错误页，也可以将错误信息转 json 返回接口
        log.error("fsdfsd");
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
