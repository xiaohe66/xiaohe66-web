package com.xiaohe66.web.code.wx.requester;

import com.xiaohe66.common.net.ex.RequesterException;
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
        super("/jscode2session", WxCode2SessionResponse.class);
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

    // todo : 当前是测试，因此不请求网络
    @Override
    public WxCode2SessionResponse call(WxCode2SessionRequest param) throws RequesterException {

        WxCode2SessionResponse response = new WxCode2SessionResponse();
        response.setErrCode(WxCode2SessionResponse.ErrCode.OK.getCode());
        response.setOpenId("test_open_id");

        return response;
    }
}
