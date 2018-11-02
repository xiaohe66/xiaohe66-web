package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.JsonUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.base.data.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求响应统一格式
 *
 * <p>切入点为用@{@link Get}、@{@link Post}、@{@link Put}、@{@link Del}注解的四种rest接口
 *
 * <p>被切入点的返回值类型不能为基本数据类型（int,long,char,boolean,float,double,byte,short），
 * 但可以是它们的包装类型（Integer,Long,Char,Boolean,Float,Double,Byte,Short），
 * 被切入点的返回值类型为基本类型时，将会抛出异常。返回值类型可以为void，为void时不会抛出异常。
 *
 * <p>该切面对被切入点的返回值进行封装，统一用{@link Result}类对返回结果进行封装
 *
 * @author xh
 * @version 1.0
 * @time 2018-07-24 11:24
 * @see Result
 */
@Aspect
@Component
public class XhResponseAsp {

    private static final Logger LOG = LoggerFactory.getLogger(XhResponseAsp.class);

    @Pointcut("@annotation(com.xiaohe66.web.base.annotation.Get)||" +
            "@annotation(com.xiaohe66.web.base.annotation.Post)||" +
            "@annotation(com.xiaohe66.web.base.annotation.Put)||" +
            "@annotation(com.xiaohe66.web.base.annotation.Del)")
    private void responsePointCut(){}

    @Around("responsePointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws IOException {
        Result result;
        try{
            Object retVal = proceedingJoinPoint.proceed();
            result = Result.ok(retVal);
        }catch (Throwable e){
            if(e instanceof MsgException){
                LOG.info(e.toString()+",msg="+e.getMessage());
                result = Result.err(((XhException) e).getCode(),e.getMessage());
            }else if(e instanceof XhException){
                /*
                * xh todo:异常的输出需要更好的管理
                * 1.提示类型的错误不应该输出完整日志（如：密码错误）
                * */
                LOG.error(e.getMessage(),e);
                result = Result.err(((XhException) e).getCode(),e.getMessage());
            }else{
                LOG.error("系统异常",e);
                result = Result.err(CodeEnum.EXCEPTION,e.getMessage());
            }
        }
        HttpServletResponse response = WebUtils.getResponse();
        response.setContentType(Final.Str.HEADER_JSON_UTF_8);
        response.getWriter().print(JsonUtils.toString(result));
        return null;
    }
}
