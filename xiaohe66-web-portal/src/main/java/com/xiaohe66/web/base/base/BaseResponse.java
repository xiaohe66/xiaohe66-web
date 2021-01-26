package com.xiaohe66.web.base.base;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaohe
 * @time 2019.12.10 15:39
 */
@Data
public abstract class BaseResponse implements Serializable {

    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;

}
