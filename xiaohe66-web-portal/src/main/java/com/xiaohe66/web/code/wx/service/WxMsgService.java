package com.xiaohe66.web.code.wx.service;

import com.xiaohe66.common.net.ex.RequesterException;
import com.xiaohe66.web.base.holder.WxAccessTokenHolder;
import com.xiaohe66.web.code.wx.request.WxSubscribeMessageSendRequest;
import com.xiaohe66.web.code.wx.requester.WxSubscribeMessageSendRequester;
import com.xiaohe66.web.code.wx.response.WxSubscribeMessageSendResponse;
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

    private WxSubscribeMessageSendRequester sendRequester;
    private WxConfig wxConfig;

    public boolean sendWxMsg(String templateId, String openId, Map<String, Object> data) {

        WxSubscribeMessageSendRequest request = new WxSubscribeMessageSendRequest();

        request.setAccessToken(WxAccessTokenHolder.get());
        request.setToUser(openId);
        request.setMiniProgramState(wxConfig.getMiniProgramState());
        request.setTemplateId(templateId);
        request.setData(data);

        try {
            WxSubscribeMessageSendResponse response = sendRequester.call(request);
            Integer errCode = response.getErrCode();
            if (errCode != null && errCode != 0) {
                log.error("send wx msg error, templateId : {}, openId : {}, data : {}", templateId, openId, data);
                return false;
            }
            return true;

        } catch (RequesterException e) {
            log.error("send wx msg error, templateId : {}, openId : {}, data : {}", templateId, openId, data, e);
            return false;
        }
    }

}
