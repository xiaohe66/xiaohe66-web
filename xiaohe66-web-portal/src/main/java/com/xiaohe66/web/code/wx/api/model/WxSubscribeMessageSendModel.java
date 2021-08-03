package com.xiaohe66.web.code.wx.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xiaohe66.common.api.IApiModel;
import lombok.Data;

import java.util.Map;

/**
 * @author xiaohe
 * @time 2021.01.26 14:50
 */
@Data
public class WxSubscribeMessageSendModel implements IApiModel {

    @Expose
    private String accessToken;

    @SerializedName("touser")
    private String toUser;

    @SerializedName("template_id")
    private String templateId;

    private String page;

    private Map<String, Object> data;

    @SerializedName("miniprogram_state")
    private String miniProgramState;

}
