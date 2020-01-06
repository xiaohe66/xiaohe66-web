package com.xiaohe66.web.sys.aop;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.org.helper.UserHelper;
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
            Integer currentUserId = UserHelper.getCurrentUsrIdNotEx();
            CodeEnum code = e.getCode();

            // todo : 代码重复了，考虑合并代码
            // 消息类
            if (e instanceof MsgException) {
                log.info("错误消息, 当前用户 : {}, code : {}, message : {}", currentUserId, code, e.getMessage());
                log.debug(code.toString(), e);
            }
            // 其它
            else {
                log.error("系统发生错误, 当前用户 : {}, code : {}", currentUserId, e);
            }


        } catch (Throwable exception) {
            log.error("系统异常", exception);
        }
        request.setAttribute("msg", "系统繁忙");
        return ERROR_PAGE_PATH;
    }
}
