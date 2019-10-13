package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.JsonUtils;
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
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {

        log.debug("catch到全局异常", e);

        Result result = null;

        // xiaohe
        if (e instanceof XhException) {
            XhException e1 = ((XhException) e);
            if (CodeEnum.NOT_LOGGED_IN.code() == e1.getCode().code()) {
                try {
                    response.sendRedirect("/notLoggedIn");
                } catch (IOException e2) {
                    throw new XhException(CodeEnum.IO_EXCEPTION);
                }
            } else {
                result = Result.err(((XhException) e).getCode(), e.getMessage());
            }
        }

        // shrio
        else if (e instanceof ShiroException) {
            log.info("没有操作权限, message:{}", e.getMessage());

            result = Result.err(CodeEnum.NOT_PERMISSION, e.getMessage());
        }

        // other
        else {
            log.error("发生未知异常", e);
            result = Result.err(CodeEnum.EXCEPTION, e.getMessage());
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