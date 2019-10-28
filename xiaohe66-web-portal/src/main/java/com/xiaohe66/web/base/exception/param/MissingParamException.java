package com.xiaohe66.web.base.exception.param;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * 缺少参数异常
 *
 * <p>该异常不会发送至客户端，或者发送至客户端时，需要转换成其它友好异常信息
 *
 * @author xiaohe
 * @time 2019.10.27 19:51
 */
public class MissingParamException extends IllegalParamException {

    public MissingParamException() {
        super(CodeEnum.B1_MISSING_PARAM, "缺少参数");
    }

    public MissingParamException(String paramName) {
        super(CodeEnum.B1_MISSING_PARAM, "缺少参数 : " + paramName);
    }
}
