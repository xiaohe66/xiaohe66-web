package com.xiaohe66.web.infrastructure.acl.wx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiaohe66.common.api.IApiModel;
import lombok.Data;

import java.util.Map;

/**
 * @author xiaohe
 * @time 2021.01.26 14:50
 */
@Data
public class WxSubscribeMessageSendModel implements IApiModel {

    private String accessToken;

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("template_id")
    private String templateId;

    private String page;

    private Map<String, Object> data;

    @JsonProperty("miniprogram_state")
    private String miniProgramState;

}
