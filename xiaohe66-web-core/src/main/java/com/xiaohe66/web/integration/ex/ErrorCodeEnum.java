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
    ILLEGAL_OPERATE(40001, "非法操作"),

    /**
     * 权限 - 401
     * 401-0* = 登录相关
     * 401-1* = 功能权限
     * 401-2* = 数据权限
     */
    NOT_LOGIN(40100, "未登录"),
    NOT_FOUND_ACCOUNT(40101, "账户不存在"),
    INVALID_TOKEN(40102, "无效的令牌"),

    NOT_FUNCTION_PERMISSION(40110, "无操作权限"),
    NOT_DATA_PERMISSION(40120, "无操作权限"),

    NOT_FOUND(40400, "对象不存在"),

    ERROR(50000, "系统繁忙");

    private final int code;
    private final String msg;

}
