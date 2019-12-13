package com.xiaohe66.web.code.wx.requester;

import com.xiaohe66.web.base.base.BaseRequester;
import com.xiaohe66.web.code.wx.request.WxCode2SessionRequest;
import com.xiaohe66.web.code.wx.response.WxCode2SessionResponse;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
//@Component
public class WxCode2SessionRequester extends BaseRequester<WxCode2SessionRequest, WxCode2SessionResponse> {

    @Value("${wx.code2SessionUrl}")
    private String apiUrl;

    @Override
    protected Request buildRequest(WxCode2SessionRequest request) {

        String url = apiUrl + "appid=" + request.getAppId()
                + "&secret=" + request.getAppSecret()
                + "&js_code=" + request.getCode()
                + "&grant_type=authorization_code";

        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

}
