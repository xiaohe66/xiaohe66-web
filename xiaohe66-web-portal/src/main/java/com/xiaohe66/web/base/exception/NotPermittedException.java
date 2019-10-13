package com.xiaohe66.web.base.exception;

/**
 * @author xiaohe
 * @date 2019.10.13 14:37
 */
public class NotPermittedException extends GeneralException {


    public NotPermittedException() {
        super("not permitted");
    }

    public NotPermittedException(String message) {
        super("not permitted:" + message);
    }
}
