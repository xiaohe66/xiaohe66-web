package com.xiaohe66.web.base.exception;

import com.xiaohe66.common.ex.XhRuntimeException;

/**
 * 在判断当前操作者 没有权限操作时，抛出该异常
 *
 * @author xiaohe
 * @time 2019.10.13 14:37
 */
public class NotPermittedException extends XhRuntimeException {

    public NotPermittedException() {
        super("not permitted");
    }

    public NotPermittedException(String message) {
        super("not permitted:" + message);
    }
}
