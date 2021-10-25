package com.xiaohe66.web.infrastructure.acl.wx.request;

import com.xiaohe66.web.infrastructure.acl.wx.BaseWxApiRequest;
import com.xiaohe66.web.infrastructure.acl.wx.model.WxSubscribeMessageSendModel;
import com.xiaohe66.web.infrastructure.acl.wx.response.WxSubscribeMessageSendResponse;
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
