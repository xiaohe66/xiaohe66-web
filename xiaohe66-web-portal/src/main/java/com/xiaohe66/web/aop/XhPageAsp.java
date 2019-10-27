package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
@Slf4j
public class XhPageAsp {

    private static final String ERROR_PAGE_PATH = "common/error";

    @Pointcut("@annotation(com.xiaohe66.web.base.annotation.Page)")
    private void pagingPointCut() {
        // 切面
    }

    @Around("pagingPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws IOException {
        HttpServletRequest request = WebUtils.getRequest();
        try {
            Object title = request.getAttribute("title");
            if (title == null) {
                request.setAttribute("title", Final.Str.DEFAULT_WEB_TITLE);
            }
            return proceedingJoinPoint.proceed();

        } catch (XhWebException e) {
            log.info("错误消息 : {}", e.getCode().msg());
            log.debug("错误消息 : {}", e.getCode().msg(), e);

        } catch (Throwable e) {
            log.error("系统异常", e);
        }
        request.setAttribute("msg", "系统繁忙");
        return ERROR_PAGE_PATH;
    }
}
