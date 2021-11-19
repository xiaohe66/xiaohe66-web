package com.xiaohe66.web.application.aop;

import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @since 2021.11.19 14:44
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class CheckLoginAop {

    private final SecurityService securityService;

    @Pointcut("@annotation(com.xiaohe66.web.application.aop.annotation.NeedLogin)")
    private void loginPointcut(){
        // aop
    }

    @Before("loginPointcut()")
    public void before(JoinPoint joinPoint){
        log.debug("check login status");

        securityService.checkLogin();
    }

}
