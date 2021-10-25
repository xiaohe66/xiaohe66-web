package com.xiaohe66.web.infrastructure.acl.wx.model;

import com.xiaohe66.common.api.IApiModel;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
@Data
public class WxCode2SessionModel implements IApiModel {

    private String appId;
    private String appSecret;
    private String code;
}
