package com.xiaohe66.web.application.sys.sec.bo;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.10.28 16:07
 */
@Data
public class WxLoginBo {

    /**
     * 微信登录 code
     */
    private String code;

    private Type type;

    public enum Type {
        /**
         *
         */
        TASK, LOVE
    }

}
