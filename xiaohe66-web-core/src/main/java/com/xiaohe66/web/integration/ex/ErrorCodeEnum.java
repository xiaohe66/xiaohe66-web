package com.xiaohe66.web.integration.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.12 15:05
 */
@AllArgsConstructor
@Getter
@ToString
public enum ErrorCodeEnum {

    /**
     * 枚举
     * 400** : 客户异常
     * 500** : 服务异常
     * <p>
     * 原则上以 http code + 编号组成错误码
     */
    PARAM_ERROR(40000, "参数错误"),
    NOT_LOGIN(40100, "未登录"),

    NOT_FOUND(40400, "操作对象不存在"),

    ERROR(50000, "系统繁忙");

    private final int code;
    private final String msg;

}
