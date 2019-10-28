package com.xiaohe66.web.base.exception.sec;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * @author xiaohe
 * @time 2019.10.28 14:07
 */
public class UnImplException extends IllegalOperationException {

    public UnImplException() {
        super(CodeEnum.B2_ILLEGAL_OPERATE, "未实现");
    }

    public UnImplException(String function) {
        super(CodeEnum.B2_ILLEGAL_OPERATE, "未实现 : " + function);
    }
}
