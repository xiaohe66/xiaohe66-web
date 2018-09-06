package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.data.ParamFinal;
import com.xiaohe66.web.base.util.WebUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * pageController切面
 *
 * @author xh
 * @version 1.0
 * @time 2018-09-06 17:31
 */
@Aspect
@Component
public class XhPageAsp {


    @Pointcut("@annotation(com.xiaohe66.web.base.annotation.Page)")
    private void pagingPointCut(){}

    @AfterReturning("pagingPointCut()")
    public void after(JoinPoint joinPoint){
        HttpServletRequest request = WebUtils.getRequest();
        Object title = request.getAttribute("title");
        if(title == null){
            request.setAttribute("title",ParamFinal.DEFAULT_WEB_TITLE);
        }
    }
}
