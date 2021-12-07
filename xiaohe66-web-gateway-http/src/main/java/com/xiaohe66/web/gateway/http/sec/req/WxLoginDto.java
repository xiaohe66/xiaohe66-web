package com.xiaohe66.web.gateway.http.sec.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaohe
 * @since 2021.11.12 17:16
 */
@Data
public class WxLoginDto {

    /**
     * 微信登录 code
     */
    @NotBlank
    private String code;

    @NotBlank
    private String type;

}
