package com.xiaohe66.web.code.wx.requester;

import com.xiaohe66.web.base.base.BaseRequester;
import com.xiaohe66.web.code.wx.request.WxCode2SessionRequest;
import com.xiaohe66.web.code.wx.response.WxCode2SessionResponse;
import com.xiaohe66.web.config.WxConfig;
import okhttp3.Request;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
//@Component
public class WxCode2SessionRequester extends BaseRequester<WxCode2SessionRequest, WxCode2SessionResponse> {

    private WxConfig config;

    public WxCode2SessionRequester(WxConfig config) {
        this.config = config;
    }

    @Override
    protected Request buildRequest(WxCode2SessionRequest request) {

        String url = config.getCode2SessionUrl() + "appid=" + request.getAppId()
                + "&secret=" + request.getAppSecret()
                + "&js_code=" + request.getCode()
                + "&grant_type=authorization_code";

        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

}
