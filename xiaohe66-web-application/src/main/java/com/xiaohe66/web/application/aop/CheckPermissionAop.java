package com.xiaohe66.web.application.aop;

import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.application.aop.annotation.NeedPermission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xiaohe
 * @since 2021.11.19 16:52
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class CheckPermissionAop {

    private final SecurityService securityService;

    @Pointcut("@annotation(com.xiaohe66.web.application.aop.annotation.NeedPermission)")
    private void pointcut() {
        // aop
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();
        NeedPermission needRoles = method.getAnnotation(NeedPermission.class);

        String[] permission = needRoles.value();

        if (log.isDebugEnabled()) {
            log.debug("check permissions : {}", Arrays.toString(permission));
        }

        if (permission != null) {
            securityService.checkPermission(permission);
        }

    }
}
