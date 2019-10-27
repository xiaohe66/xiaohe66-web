package com.xiaohe66.web.base.exception;

import com.xiaohe66.common.ex.XhRuntimeException;
import com.xiaohe66.web.aop.GlobalExceptionHandler;
import com.xiaohe66.web.base.data.CodeEnum;

/**
 * 消息类异常，一般用于参数验证错误
 *
 * <p>抛出该异常会在 {@link GlobalExceptionHandler} 类进行统一处理，
 * 并将 {@link CodeEnum} 中的错误描述发送至客户端
 *
 * <p>若异常包装了另一个异常，则会在全球异常处理时打印堆栈信息，若该异常未包装另一个异常，则只会打印一行错误信息
 *
 * <p>该异常的message信息会被打印出来
 *
 * todo : 这种方式好像不是很好，是不是应该考虑将该类改为消息类，即该异常类仅作为返回消息的，而不打印堆站
 *
 * @author xiaohe
 * @time 2019-10-18 09:40
 * @see GlobalExceptionHandler
 * @see CodeEnum
 */
public class XhWebException extends XhRuntimeException {

    private final CodeEnum code;

    public XhWebException(CodeEnum code) {
        this(code, code.msg());
    }

    public XhWebException(CodeEnum code, String message) {
        super(message);
        this.code = code;
    }

    public XhWebException(CodeEnum code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public XhWebException(CodeEnum code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public CodeEnum getCode() {
        return code;
    }
}
