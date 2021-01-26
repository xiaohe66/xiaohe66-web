package com.xiaohe66.web.code.wx.request;

import com.xiaohe66.web.base.base.BaseRequest;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2021.01.26 14:33
 */
@Data
public class WxGetAccessTokenRequest implements BaseRequest {

    private String grantType;
    private String appId;
    private String secret;

}
