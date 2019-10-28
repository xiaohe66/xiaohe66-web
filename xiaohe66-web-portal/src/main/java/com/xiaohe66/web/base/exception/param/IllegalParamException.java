package com.xiaohe66.web.base.exception.param;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;

/**
 * 在系统级别的操作中，需要某个参数时但是调用方未传入时，抛出该异常
 *
 * <p>该异常的信息不返回给客户端，返回给客户端的信息需要转换成友好的形式
 *
 * @author xiaohe
 * @time 2019.10.27 19:07
 */
public class IllegalParamException extends XhWebException {

    protected IllegalParamException(CodeEnum code) {
        super(code);
    }

    protected IllegalParamException(CodeEnum code, String message) {
        super(code, message);
    }

    protected IllegalParamException(CodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    protected IllegalParamException(CodeEnum code, Throwable cause) {
        super(code, cause);
    }

    public IllegalParamException() {
        super(CodeEnum.B1_ILLEGAL_PARAM);
    }

    public IllegalParamException(String message) {
        super(CodeEnum.B1_ILLEGAL_PARAM, message);
    }

    public IllegalParamException(String message, Throwable cause) {
        super(CodeEnum.B1_ILLEGAL_PARAM, message, cause);
    }

    public IllegalParamException(Throwable cause) {
        super(CodeEnum.B1_ILLEGAL_PARAM, cause);
    }
}
