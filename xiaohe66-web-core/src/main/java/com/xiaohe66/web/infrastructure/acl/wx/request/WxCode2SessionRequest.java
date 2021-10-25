package com.xiaohe66.web.infrastructure.acl.wx.request;

import com.xiaohe66.web.infrastructure.acl.wx.BaseWxApiRequest;
import com.xiaohe66.web.infrastructure.acl.wx.model.WxCode2SessionModel;
import com.xiaohe66.web.infrastructure.acl.wx.response.WxCode2SessionResponse;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
@Component
public class WxCode2SessionRequest extends BaseWxApiRequest<WxCode2SessionResponse> {

    public WxCode2SessionRequest() {
        super(Method.GET);
    }

    @Override
    protected Class<WxCode2SessionResponse> getResponseClass() {
        return WxCode2SessionResponse.class;
    }

    @Override
    public String getQueryUrl() {

        WxCode2SessionModel model = (WxCode2SessionModel) getModel();

        return "/sns/jscode2session?appid=" + model.getAppId()
                + "&secret=" + model.getAppSecret()
                + "&js_code=" + model.getCode()
                + "&grant_type=authorization_code";
    }
}
