package com.xiaohe66.web.code.sys.ex;

/**
 * @author xiaohe
 * @time 2019.10.18 17:34
 */
public class EmailSendException extends RuntimeException{

    public EmailSendException() {
    }

    public EmailSendException(String message) {
        super(message);
    }

    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSendException(Throwable cause) {
        super(cause);
    }

    public EmailSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
