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
     */
    PARAM_ERROR(40000, "参数错误");

    private final int code;
    private final String msg;

}
