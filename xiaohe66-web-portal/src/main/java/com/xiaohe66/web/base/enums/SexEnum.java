package com.xiaohe66.web.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaohe
 * @time 2021.01.11 11:54
 */
@AllArgsConstructor
@Getter
public enum SexEnum {

    /**
     * 性别
     */
    UNKNOWN(0,"未知"),
    MAN(1,"男性"),
    WOMAN(2,"女性");

    private Integer code;
    private String name;

    public static SexEnum valueOf(Integer code){
        SexEnum[] values = SexEnum.values();

        for (SexEnum sexEnum : values) {

            if (sexEnum.code.equals(code)) {
                return sexEnum;
            }
        }

        return null;
    }
}
