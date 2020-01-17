package com.boot.bvserver.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTimeAspect {
    private final static Logger logger = LoggerFactory.getLogger(MethodTimeAspect.class);

    @Pointcut("@annotation(com.boot.bvserver.annotation.MyAnnotation)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object useTime(ProceedingJoinPoint joinPoint) {
        Object out = null;
        try {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            logger.info(String.format(" Method [%s.%s()] start", className, joinPoint.getSignature().getName()));
            System.out.println(String.format(" Method [%s.%s()] start", className, joinPoint.getSignature().getName()));
            long start = System.currentTimeMillis();
            out = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info(String.format(" Method [%s.%s()] execution time:%sms", className, joinPoint.getSignature().getName(), elapsedTime));
            System.out.println(String.format(" Method [%s.%s()] execution time:%sms", className, joinPoint.getSignature().getName(), elapsedTime));
        } catch (Throwable throwable) {
            logger.error("aop record method exec time error", throwable);
        }
        return out;
    }
}