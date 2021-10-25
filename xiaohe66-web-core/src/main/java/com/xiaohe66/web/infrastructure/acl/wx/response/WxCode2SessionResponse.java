package com.xiaohe66.web.infrastructure.acl.wx.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiaohe66.web.infrastructure.acl.wx.BaseWxApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author xiaohe
 * @time 2019.12.10 15:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCode2SessionResponse extends BaseWxApiResponse {

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("unionid")
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
