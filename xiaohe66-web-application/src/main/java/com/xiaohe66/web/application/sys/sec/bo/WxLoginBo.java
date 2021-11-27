package com.xiaohe66.web.application.sys.sec.bo;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.10.28 16:07
 */
@Data
public class WxLoginBo {

    private String nickname;
    private String province;
    private String city;
    private String country;
    private String avatarUrl;
    private String sessionKey;

    /**
     * 微信登录 code
     */
    private String code;

    private Type type;

    public enum Type{
        /**
         *
         */
        TASK, LOVE
    }

}
