package com.xiaohe66.web.sys.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author xiaohe
 * @time 2020.07.25 16:52
 */
@Aspect
@Component
@Slf4j
public class PrintLogAsp {

    @Pointcut("@annotation(com.xiaohe66.web.base.annotation.PrintLog)")
    private void printLog() {
        // 切面
    }

    @Before("printLog()")
    public void before(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("{}#{}{}", signature.getDeclaringTypeName(), signature.getName(), args);
    }

    @AfterReturning(value = "printLog()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();

        log.info("{}#{}={}", signature.getDeclaringTypeName(), signature.getName(), result);
    }
}
