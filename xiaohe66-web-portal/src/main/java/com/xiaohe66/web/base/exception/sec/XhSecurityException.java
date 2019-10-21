package com.xiaohe66.web.base.exception.sec;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;

/**
 * @author xiaohe
 * @time 2019.10.21 10:51
 */
public class XhSecurityException extends XhWebException {

    public XhSecurityException(CodeEnum code) {
        super(code);
    }

    public XhSecurityException(CodeEnum code, String message) {
        super(code, message);
    }

    public XhSecurityException(CodeEnum code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public XhSecurityException(CodeEnum code, Throwable cause) {
        super(code, cause);
    }
}
