package com.xiaohe66.web.base.exception.sec;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;

/**
 * @author xiaohe
 * @time 2019.10.21 10:51
 */
public class IllegalOperationException extends XhWebException {

    protected IllegalOperationException(CodeEnum code) {
        super(code);
    }

    protected IllegalOperationException(CodeEnum code, String message) {
        super(code, message);
    }

    protected IllegalOperationException(CodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    protected IllegalOperationException(CodeEnum code, Throwable cause) {
        super(code, cause);
    }

    public IllegalOperationException() {
        super(CodeEnum.B2_ILLEGAL_OPERATE);
    }

    public IllegalOperationException(String message) {
        super(CodeEnum.B2_ILLEGAL_OPERATE, message);
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(CodeEnum.B2_ILLEGAL_OPERATE, message, cause);
    }

    public IllegalOperationException(Throwable cause) {
        super(CodeEnum.B2_ILLEGAL_OPERATE, cause);
    }
}
