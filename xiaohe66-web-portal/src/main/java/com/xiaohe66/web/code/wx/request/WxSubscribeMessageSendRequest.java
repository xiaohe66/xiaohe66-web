package com.xiaohe66.web.code.wx.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xiaohe66.web.base.base.BaseRequest;
import lombok.Data;

import java.util.Map;

/**
 * @author xiaohe
 * @time 2021.01.26 14:50
 */
@Data
public class WxSubscribeMessageSendRequest implements BaseRequest {

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
