package com.xiaohe66.web.domain.sys.sec.ex;

/**
 *
 * TODO : 该类用 {@link com.xiaohe66.web.integration.ex.BusinessException} 代替
 *
 * @author xiaohe
 * @since 2021.11.01 11:55
 */
public class LoginException extends Exception {

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
