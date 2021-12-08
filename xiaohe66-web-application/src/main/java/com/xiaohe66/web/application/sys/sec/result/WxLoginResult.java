package com.xiaohe66.web.application.sys.sec.result;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.12.08 11:16
 */
@Data
public class WxLoginResult {

    private Long id;
    private Boolean needUserInfo;
    private String sessionId;

}
