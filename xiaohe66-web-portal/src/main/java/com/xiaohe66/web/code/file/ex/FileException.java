package com.xiaohe66.web.code.file.ex;

import com.xiaohe66.web.base.exception.GeneralException;

/**
 * @author xiaohe
 * @date 2019.10.10 23:19
 */
public class FileException extends GeneralException {

    public FileException() {
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }

    public FileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
