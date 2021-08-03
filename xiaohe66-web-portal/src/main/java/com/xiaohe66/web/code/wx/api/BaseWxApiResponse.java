package com.xiaohe66.web.code.wx.api;

import com.google.gson.annotations.SerializedName;
import com.xiaohe66.common.api.BaseApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.12.10 15:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseWxApiResponse extends BaseApiResponse {

    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;

    @Override
    public boolean isSuccess() {
        // todo : 验证
        return errCode == 0;
    }
}
