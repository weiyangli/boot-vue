package com.boot.bvserver.aspect;

import com.boot.bvserver.annotation.LogInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.boot.bvserver.annotation.LogInfo)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object saveLog(ProceedingJoinPoint joinPoint) {
        Object out = null;
        try {
            System.out.println("方法执行前");
            out = joinPoint.proceed();
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            LogInfo logInfo = signature.getMethod().getAnnotation(LogInfo.class);
            // 处理日志信息
            logInfo.value();
            System.out.println("接收到注解中的值!" + logInfo.value());
            // 保存到日志表
        } catch (Throwable throwable) {
        }
        return out;
    }

}