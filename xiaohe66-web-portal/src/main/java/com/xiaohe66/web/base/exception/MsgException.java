package com.xiaohe66.web.base.exception;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * 消息类，即在统一异常处理时不打印堆栈信息
 *
 * @author xiaohe
 * @time 2019.10.28 12:13
 */
public class MsgException extends XhWebException {

    public MsgException(CodeEnum code) {
        super(code);
    }

    public MsgException(CodeEnum code, String message) {
        super(code, message);
    }

}
