package com.xiaohe66.web.code.wx.response;

import com.google.gson.annotations.SerializedName;
import com.xiaohe66.web.base.base.BaseResponse;
import lombok.Data;
import lombok.Getter;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
@Data
public class WxCode2SessionResponse implements BaseResponse {

    @SerializedName("errcode")
    private Integer errCode;

    @SerializedName("errmsg")
    private String errMsg;

    @SerializedName("openid")
    private String openId;

    @SerializedName("session_key")
    private String sessionKey;

    @SerializedName("unionid")
    private String unionId;

    @Getter
    public enum ErrCode {

        /*
        *
        *
        * */
        OK(0),

        /**
         * 系统繁忙，出现这种状态时稍后再试
         */
        TIMEOUT(-1),

        /**
         * 无效的
         */
        USELESS(40029),

        /**
         * 频率限制，每个用户每分钟100次
         */
        ERR(45011),
        ;

        private int code;

        ErrCode(int code) {
            this.code = code;
        }
    }

}
