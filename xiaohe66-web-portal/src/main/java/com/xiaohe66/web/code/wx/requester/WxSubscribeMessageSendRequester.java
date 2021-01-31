package com.xiaohe66.web.code.wx.requester;

import com.xiaohe66.web.base.base.WxHttpRequester;
import com.xiaohe66.web.code.wx.request.WxSubscribeMessageSendRequest;
import com.xiaohe66.web.code.wx.response.WxSubscribeMessageSendResponse;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2021.01.26 14:58
 */
@Component
public class WxSubscribeMessageSendRequester extends WxHttpRequester<WxSubscribeMessageSendRequest, WxSubscribeMessageSendResponse> {

    public WxSubscribeMessageSendRequester() {
        super("/cgi-bin/message/subscribe/send", WxSubscribeMessageSendResponse.class);
    }

    @Override
    protected Request buildRequest(WxSubscribeMessageSendRequest request) {

        String url = fullUrl + "?access_token=" + request.getAccessToken();

        RequestBody body = createJsonBody(request);

        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }
}
