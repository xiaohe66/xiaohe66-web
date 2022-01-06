package com.xiaohe66.web.infrastructure.acl.wx.request;

import com.fasterxml.jackson.databind.JavaType;
import com.xiaohe66.web.infrastructure.acl.wx.BaseWxApiRequest;
import com.xiaohe66.web.infrastructure.acl.wx.model.WxGetAccessTokenModel;
import com.xiaohe66.web.infrastructure.acl.wx.response.WxGetAccessTokenResponse;

/**
 * @author xiaohe
 * @time 2021.01.26 14:32
 */
public class WxGetAccessTokenRequest extends BaseWxApiRequest<WxGetAccessTokenResponse> {

    public WxGetAccessTokenRequest() {
        super(Method.GET);
    }

    @Override
    public String getQueryUrl() {

        WxGetAccessTokenModel model = (WxGetAccessTokenModel) getModel();

        return "/cgi-bin/token?grant_type=" + model.getGrantType()
                + "&appid=" + model.getAppId()
                + "&secret=" + model.getSecret();
    }

    @Override
    public JavaType getResponseType() {
        return constructType(WxGetAccessTokenResponse.class);
    }
}
