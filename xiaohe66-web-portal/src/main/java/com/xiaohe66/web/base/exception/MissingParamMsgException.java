package com.xiaohe66.web.base.exception;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * 缺少参数异常
 * <P>抛出该异常会返回 {@link CodeEnum#B1_MISSING_PARAM} 信息给客户端
 *
 * @author xiaohe
 * @time 2019.10.27 19:24
 */
public class MissingParamMsgException extends XhWebException {

    public MissingParamMsgException() {
        super(CodeEnum.B1_MISSING_PARAM);
    }

    public MissingParamMsgException(String paramName) {
        super(CodeEnum.B1_MISSING_PARAM, "缺少参数：" + paramName);
    }
}
