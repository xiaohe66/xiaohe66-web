package com.xiaohe66.web.base.exception.sec;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * @author xiaohe
 * @time 2020.07.19 00:12
 */
public class NotLoginException extends IllegalOperationException{


    public NotLoginException() {
        super(CodeEnum.B2_NOT_LOGGED_IN, "未登录");
    }
}
