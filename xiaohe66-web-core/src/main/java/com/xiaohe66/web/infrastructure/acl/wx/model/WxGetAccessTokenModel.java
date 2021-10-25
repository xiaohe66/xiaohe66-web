package com.xiaohe66.web.infrastructure.acl.wx.model;

import com.xiaohe66.common.api.IApiModel;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2021.01.26 14:33
 */
@Data
public class WxGetAccessTokenModel implements IApiModel {

    private String grantType;
    private String appId;
    private String secret;

}
