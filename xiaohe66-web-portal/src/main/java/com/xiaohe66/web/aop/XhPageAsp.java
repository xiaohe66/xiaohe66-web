package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.WebUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    private static final Logger LOG = LoggerFactory.getLogger(XhPageAsp.class);
    private static final String ERROR_PAGE_PATH = "common/error";

    @Pointcut("@annotation(com.xiaohe66.web.base.annotation.Page)")
    private void pagingPointCut(){}

    @Around("pagingPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws IOException {
        HttpServletRequest request = WebUtils.getRequest();
        try{
            Object title = request.getAttribute("title");
            if(title == null){
                request.setAttribute("title", Final.Str.DEFAULT_WEB_TITLE);
            }
            return proceedingJoinPoint.proceed();
        }catch (Throwable e){
            if(e instanceof MsgException){
                LOG.info(e.toString()+",msg="+e.getMessage());
                request.setAttribute("msg",((XhException) e).getCode().desc());
            }else if(e instanceof XhException){
                LOG.info(e.toString()+",msg="+e.getMessage(),e);
                request.setAttribute("msg","系统异常");
            }else{
                LOG.error("系统异常",e);
                request.setAttribute("msg","系统异常");
            }
            return ERROR_PAGE_PATH;
        }
    }
}
