package com.xiaohe66.web.base.exception.param;

import com.xiaohe66.common.ex.XhRuntimeException;

/**
 * @author xiaohe
 * @time 2019.10.12 09:54
 */
public class EmptyException extends XhRuntimeException {

    public EmptyException() {
        this("param cannot be empty");
    }

    public EmptyException(String message) {
        super(message);
    }

    public EmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyException(Throwable cause) {
        super(cause);
    }

    public EmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
