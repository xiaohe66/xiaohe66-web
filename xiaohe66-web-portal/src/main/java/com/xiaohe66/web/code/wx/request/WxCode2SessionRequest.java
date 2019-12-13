package com.xiaohe66.web.code.wx.request;

import com.xiaohe66.web.base.base.BaseRequest;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
@Data
public class WxCode2SessionRequest implements BaseRequest {

    private String appId;
    private String appSecret;
    private String code;
}
