package com.xiaohe66.web.code.wx.requester;

import com.xiaohe66.web.base.base.WxHttpRequester;
import com.xiaohe66.web.code.wx.request.WxGetAccessTokenRequest;
import com.xiaohe66.web.code.wx.response.WxGetAccessTokenResponse;
import okhttp3.Request;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2021.01.26 14:32
 */
@Component
public class WxGetAccessTokenRequester extends WxHttpRequester<WxGetAccessTokenRequest, WxGetAccessTokenResponse> {

    public WxGetAccessTokenRequester() {
        super("/cgi-bin/token", WxGetAccessTokenResponse.class);
    }

    @Override
    protected Request buildRequest(WxGetAccessTokenRequest request) {

        String url = fullUrl + "?grant_type=" + request.getGrantType()
                + "&appid=" + request.getAppId()
                + "&secret=" + request.getSecret();

        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }
}
