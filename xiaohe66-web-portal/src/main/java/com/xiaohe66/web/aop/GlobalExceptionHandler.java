package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.JsonUtils;
import com.xiaohe66.web.base.data.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        LOGGER.error("exception",e);
        Result result = null;
        if(e instanceof XhException){
            XhException e1 = ((XhException) e);
            if (CodeEnum.NOT_LOGGED_IN.code() == e1.getCode().code()) {
                try {
                    response.sendRedirect("/notLoggedIn");
                } catch (IOException e2) {
                    throw new XhException(CodeEnum.IO_EXCEPTION);
                }
            }else{
                result = Result.err(((XhException) e).getCode(),e.getMessage());
            }
        }else{
            result = Result.err(CodeEnum.EXCEPTION,e.getMessage());
        }
        try {
            response.setContentType(Final.Str.HEADER_JSON_UTF_8);
            response.getWriter().print(JsonUtils.toString(result));
        } catch (IOException e1) {
            LOGGER.error("getWriter() exception");
        }
        return new ModelAndView();
    }
}