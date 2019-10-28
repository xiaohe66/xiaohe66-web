package com.xiaohe66.web.base.exception;

import com.xiaohe66.common.ex.XhRuntimeException;
import com.xiaohe66.web.aop.GlobalExceptionHandler;
import com.xiaohe66.web.base.data.CodeEnum;

/**
 * 消息类异常，一般用于参数验证错误
 *
 * 1. 所有的异常都要友好的返回给B，即可知的异常，返回对应的提示。未知的异常，统一返回 系统繁忙。
 * 1.1.非检查异常分打印堆栈和不打印堆栈两种，且构造方法中都要加入CodeEnum类。检查异常看情况新建，在抛出后，在使用时catch并处理返回非检查异常。
 * 2. 能在Controller检查的异常，尽量在Controller中检查，在controller类中抛出的异常不打印堆栈信息，除了controller外抛出的异常，都要打印堆栈信息。
 * 3. 用户输入参数类异常，在controller中检查。 - MsgException(MissingParam/IllegalParam)
 * 4. 权限相关异常，统一增删改查在controller中检查。 - MsgException(IlleagOperate)
 * 4.1.部分不方便检查的，如需要创建者自己修改自己的权限。则在service中检查。- MsgException(IlleagOperate)
 *
 * @author xiaohe
 * @time 2019-10-18 09:40
 * @see GlobalExceptionHandler
 * @see CodeEnum
 * @see <a href="https://www.processon.com/view/link/5db6657fe4b09df55184e803">UML图</a>
 *
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
