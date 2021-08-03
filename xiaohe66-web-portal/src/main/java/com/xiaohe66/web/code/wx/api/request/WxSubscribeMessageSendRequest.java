package com.xiaohe66.web.code.wx.api.request;

import com.xiaohe66.web.code.wx.api.BaseWxApiRequest;
import com.xiaohe66.web.code.wx.api.model.WxSubscribeMessageSendModel;
import com.xiaohe66.web.code.wx.api.response.WxSubscribeMessageSendResponse;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2021.01.26 14:58
 */
@Component
public class WxSubscribeMessageSendRequest extends BaseWxApiRequest<WxSubscribeMessageSendResponse> {

    public WxSubscribeMessageSendRequest() {
        super(Method.POST);
    }

    @Override
    protected Class<WxSubscribeMessageSendResponse> getResponseClass() {
        return WxSubscribeMessageSendResponse.class;
    }

    @Override
    public String getQueryUrl() {

        WxSubscribeMessageSendModel model = (WxSubscribeMessageSendModel) getModel();

        return "/cgi-bin/message/subscribe/send?access_token=" + model.getAccessToken();
    }
}
