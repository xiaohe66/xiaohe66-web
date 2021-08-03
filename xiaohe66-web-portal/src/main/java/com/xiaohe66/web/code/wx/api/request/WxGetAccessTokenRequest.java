package com.xiaohe66.web.code.wx.api.request;

import com.xiaohe66.web.code.wx.api.BaseWxApiRequest;
import com.xiaohe66.web.code.wx.api.model.WxGetAccessTokenModel;
import com.xiaohe66.web.code.wx.api.response.WxGetAccessTokenResponse;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2021.01.26 14:32
 */
@Component
public class WxGetAccessTokenRequest extends BaseWxApiRequest<WxGetAccessTokenResponse> {

    public WxGetAccessTokenRequest() {
        super(Method.GET);
    }

    @Override
    protected Class<WxGetAccessTokenResponse> getResponseClass() {
        return WxGetAccessTokenResponse.class;
    }

    @Override
    public String getQueryUrl() {

        WxGetAccessTokenModel model = (WxGetAccessTokenModel) getModel();

        return "/cgi-bin/token?grant_type=" + model.getGrantType()
                + "&appid=" + model.getAppId()
                + "&secret=" + model.getSecret();
    }
}
