package com.xiaohe66.web.application.request;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.10.28 16:07
 */
@Data
public class WxLoginRequest {

    private String nickname;
    private String province;
    private String city;
    private String country;
    private String avatarUrl;
    private String sessionKey;

}
