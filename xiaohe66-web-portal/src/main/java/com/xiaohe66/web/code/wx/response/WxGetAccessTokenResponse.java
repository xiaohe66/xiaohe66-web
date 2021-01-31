package com.xiaohe66.web.code.wx.response;

import com.google.gson.annotations.SerializedName;
import com.xiaohe66.web.base.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2021.01.26 14:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxGetAccessTokenResponse extends BaseResponse {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private Integer expiresIn;

}
