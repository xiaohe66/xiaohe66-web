package com.xiaohe66.web.code.wx.service;

import com.xiaohe66.common.api.ApiException;
import com.xiaohe66.web.base.holder.WxAccessTokenHolder;
import com.xiaohe66.web.code.wx.api.WxApiClient;
import com.xiaohe66.web.code.wx.api.model.WxSubscribeMessageSendModel;
import com.xiaohe66.web.code.wx.api.request.WxSubscribeMessageSendRequest;
import com.xiaohe66.web.code.wx.api.response.WxSubscribeMessageSendResponse;
import com.xiaohe66.web.config.WxConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiaohe
 * @time 2021.01.26 15:48
 */
@Service
@AllArgsConstructor
@Slf4j
public class WxMsgService {

    private final WxApiClient client;
    private final WxConfig wxConfig;

    public boolean sendWxMsg(String templateId, String openId, Map<String, Object> data) {

        WxSubscribeMessageSendModel model = new WxSubscribeMessageSendModel();

        model.setAccessToken(WxAccessTokenHolder.get());
        model.setToUser(openId);
        model.setMiniProgramState(wxConfig.getMiniProgramState());
        model.setTemplateId(templateId);
        model.setData(data);

        WxSubscribeMessageSendRequest request = new WxSubscribeMessageSendRequest();
        request.setModel(model);

        try {

            WxSubscribeMessageSendResponse response = client.execute(request);
            Integer errCode = response.getErrCode();
            if (errCode != null && errCode != 0) {
                log.error("send wx msg error, templateId : {}, openId : {}, data : {}", templateId, openId, data);
                return false;
            }
            return true;

        } catch (ApiException e) {
            log.error("send wx msg error, templateId : {}, openId : {}, data : {}", templateId, openId, data, e);
            return false;
        }
    }

}
