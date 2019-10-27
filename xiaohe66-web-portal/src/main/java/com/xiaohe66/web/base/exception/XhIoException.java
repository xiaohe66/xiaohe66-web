package com.xiaohe66.web.base.exception;

import com.xiaohe66.common.ex.XhException;

/**
 * @author xiaohe
 * @time 2019.10.27 20:09
 */
public class XhIoException extends XhException {

    public XhIoException() {
    }

    public XhIoException(String message) {
        super(message);
    }

    public XhIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public XhIoException(Throwable cause) {
        super(cause);
    }

    public XhIoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
