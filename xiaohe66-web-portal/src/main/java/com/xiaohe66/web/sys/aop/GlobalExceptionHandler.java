package com.xiaohe66.web.sys.aop;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.exception.sec.MissingFunctionException;
import com.xiaohe66.web.base.util.JsonUtils;
import com.xiaohe66.web.code.org.helper.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaohe
 * @time 17-10-31 031
 */
@Component
@Slf4j
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception exception) {

        log.debug("catch到全局异常 : {}", exception.getMessage());

        Result result;

        Integer currentUserId = UserHelper.getCurrentUsrIdNotEx();

        // 我的异常
        if (exception instanceof XhWebException) {

            XhWebException e = ((XhWebException) exception);
            CodeEnum code = e.getCode();

            // 消息类
            if (e instanceof MsgException) {

                if (log.isDebugEnabled()) {
                    log.debug(code.toString(), e);
                } else {
                    log.info("错误消息, 当前用户 : {}, code : {}, message : {}", currentUserId, code, e.getMessage());
                }

            } else if (e instanceof MissingFunctionException) {
                if (log.isDebugEnabled()) {
                    log.debug(e.getMessage(), e);
                } else {
                    log.info(e.getMessage());
                }
            }
            // 其它
            else {
                log.error("系统发生错误, 当前用户 : {}, code : {}", currentUserId, e);
            }
            result = Result.err(code);
        }

        // shrio
        else if (exception instanceof ShiroException) {
            log.error("没有操作权限, 当前用户 : {}", currentUserId, exception);
            result = Result.err(CodeEnum.B2_ILLEGAL_OPERATE);
        }

        // other
        else {
            log.error("发生未知异常, 当前用户 : {}", currentUserId, exception);
            result = Result.err(CodeEnum.EXCEPTION);
        }

        try {
            response.setContentType(Final.HeaderKey.JSON_UTF_8);
            response.getWriter().print(JsonUtils.toString(result));
        } catch (IOException e1) {
            log.error("getWriter() exception", e1);
        }
        return new ModelAndView();
    }
}