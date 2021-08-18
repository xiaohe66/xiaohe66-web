package com.xiaohe66.web.integration.domain;

import lombok.Getter;

/**
 * @author xiaohe
 * @since 2021.08.18 10:40
 */
public class MsgException extends RuntimeException {

    @Getter
    private final String msg;

    public MsgException(String msg) {
        this.msg = msg;
    }

    public MsgException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public MsgException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public MsgException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public MsgException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = msg;
    }
}
