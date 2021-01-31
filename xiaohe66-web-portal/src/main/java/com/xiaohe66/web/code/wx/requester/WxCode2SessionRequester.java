package com.xiaohe66.web.code.wx.requester;

import com.xiaohe66.web.base.base.WxHttpRequester;
import com.xiaohe66.web.code.wx.request.WxCode2SessionRequest;
import com.xiaohe66.web.code.wx.response.WxCode2SessionResponse;
import okhttp3.Request;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
@Component
public class WxCode2SessionRequester extends WxHttpRequester<WxCode2SessionRequest, WxCode2SessionResponse> {

    public WxCode2SessionRequester() {
        super("/sns/jscode2session", WxCode2SessionResponse.class);
    }

    @Override
    protected Request buildRequest(WxCode2SessionRequest request) {

        String url = fullUrl + "?appid=" + request.getAppId()
                + "&secret=" + request.getAppSecret()
                + "&js_code=" + request.getCode()
                + "&grant_type=authorization_code";

        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }
}
