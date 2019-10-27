package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.IllegalParamException;
import com.xiaohe66.web.base.exception.MissingParamException;
import com.xiaohe66.web.base.exception.XhWebException;
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

        // 消息类
        if (exception instanceof XhWebException) {
            XhWebException e = ((XhWebException) exception);
            CodeEnum code = e.getCode();
            if (e.getCause() == null) {
                log.info("正常消息, 当前用户 : {}, message : {}, code : {}", currentUserId, e.getMessage(), code);
                log.debug(code.toString(), e);
            } else {
                log.error("错误消息, 当前用户 : {}, code : {}", currentUserId, code, e);
            }
            result = Result.err(code);
        }

        // 系统级别异常
        else if (exception instanceof IllegalParamException ||
                exception instanceof MissingParamException) {
            log.error("系统运行发生错误", exception);
            result = Result.err(CodeEnum.EXCEPTION);
        }

        // shrio
        else if (exception instanceof ShiroException) {
            log.info("没有操作权限, 当前用户 : {}", currentUserId, exception);
            log.debug("没有操作权限", exception);
            result = Result.err(CodeEnum.B2_ILLEGAL_OPERATE);
        }

        // other
        else {
            log.error("发生未知异常, 当前用户 : {}", currentUserId, exception);
            result = Result.err(CodeEnum.EXCEPTION);
        }

        try {
            response.setContentType(Final.Str.HEADER_JSON_UTF_8);
            response.getWriter().print(JsonUtils.toString(result));
        } catch (IOException e1) {
            log.error("getWriter() exception");
        }
        return new ModelAndView();
    }
}