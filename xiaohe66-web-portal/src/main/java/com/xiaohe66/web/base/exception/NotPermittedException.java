package com.xiaohe66.web.base.exception;

import com.xiaohe66.common.ex.XhRuntimeException;

/**
 * @author xiaohe
 * @date 2019.10.13 14:37
 */
public class NotPermittedException extends XhRuntimeException {


    public NotPermittedException() {
        super("not permitted");
    }

    public NotPermittedException(String message) {
        super("not permitted:" + message);
    }
}
