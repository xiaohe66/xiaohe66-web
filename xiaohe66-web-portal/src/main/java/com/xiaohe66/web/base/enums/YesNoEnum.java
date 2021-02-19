package com.xiaohe66.web.base.enums;

/**
 * @author xiaohe
 * @time 2021.02.19 10:49
 */
public enum YesNoEnum {

    /**
     * 是否
     */
    NO(0),
    YES(1);

    private int code;

    YesNoEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
