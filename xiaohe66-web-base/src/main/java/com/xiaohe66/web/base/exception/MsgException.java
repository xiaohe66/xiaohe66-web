package com.xiaohe66.web.base.exception;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * @author xh
 * @date 18-10-18 018
 */
public class MsgException extends XhException{
    public MsgException(CodeEnum code) {
        super(code);
    }

    public MsgException(CodeEnum code, String message) {
        super(code, message);
    }

    public MsgException(CodeEnum code, Throwable cause) {
        super(code, cause);
    }
}
