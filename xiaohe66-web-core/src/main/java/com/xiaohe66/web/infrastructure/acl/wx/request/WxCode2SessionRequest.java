package com.xiaohe66.web.infrastructure.acl.wx.request;

import com.fasterxml.jackson.databind.JavaType;
import com.xiaohe66.web.infrastructure.acl.wx.BaseWxApiRequest;
import com.xiaohe66.web.infrastructure.acl.wx.model.WxCode2SessionModel;
import com.xiaohe66.web.infrastructure.acl.wx.response.WxCode2SessionResponse;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
public class WxCode2SessionRequest extends BaseWxApiRequest<WxCode2SessionResponse> {

    public WxCode2SessionRequest() {
        super(Method.GET);
    }

    @Override
    public String getQueryUrl() {

        WxCode2SessionModel model = (WxCode2SessionModel) getModel();

        return "/sns/jscode2session?appid=" + model.getAppId()
                + "&secret=" + model.getAppSecret()
                + "&js_code=" + model.getCode()
                + "&grant_type=authorization_code";
    }

    @Override
    public JavaType getResponseType() {
        return constructType(WxCode2SessionResponse.class);
    }
}
