package com.meijianwei.autolog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(com.huawei.genex.common.annotation.MethodLog)")
    protected void logPointcut(){}

    @Before("logPointcut()")
    protected void doBefore(JoinPoint joinPoint){
        log.info("className:{}",joinPoint.getSignature().getDeclaringType());
        log.info("methodName:{}",joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(returning = "object", value = "logPointcut()")
    protected void doAfterReturning(Object object){
        log.info("result:{}",object);
    }


}
