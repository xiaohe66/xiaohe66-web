package com.xiaohe66.web.base.exception;

import com.xiaohe66.common.ex.XhRuntimeException;

/**
 * 在系统级别的操作中，需要某个参数时但是调用方未传入时，抛出该异常
 *
 * <p>该异常的信息不返回给客户端，返回给客户端的信息需要转换成友好的形式
 *
 * @author xiaohe
 * @time 2019.10.27 19:07
 */
public class IllegalParamException extends XhRuntimeException {

    public IllegalParamException() {
    }

    public IllegalParamException(String message) {
        super(message);
    }

    public IllegalParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParamException(Throwable cause) {
        super(cause);
    }

    public IllegalParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
