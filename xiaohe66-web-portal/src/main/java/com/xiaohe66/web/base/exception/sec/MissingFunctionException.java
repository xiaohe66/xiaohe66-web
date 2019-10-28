package com.xiaohe66.web.base.exception.sec;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * @author xiaohe
 * @time 2019.10.28 12:36
 */
public class MissingFunctionException extends IllegalOperationException {

    public MissingFunctionException() {
        super(CodeEnum.B2_MISSING_PERMITTED, "缺少功能权限");
    }

    public MissingFunctionException(String functionName) {
        super(CodeEnum.B2_MISSING_PERMITTED, "缺少功能权限 : " + functionName);
    }
}
