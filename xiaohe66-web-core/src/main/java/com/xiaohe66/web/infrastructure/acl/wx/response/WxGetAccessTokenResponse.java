package com.xiaohe66.web.infrastructure.acl.wx.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiaohe66.web.infrastructure.acl.wx.BaseWxApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2021.01.26 14:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxGetAccessTokenResponse extends BaseWxApiResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

}
