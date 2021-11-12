package com.xiaohe66.web.gateway.http.sec.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xiaohe
 * @since 2021.11.12 16:21
 */
@Data
public class LoginRequest {

    @NotBlank
    @Size(min = 1, max = 64)
    private String loginName;

    @NotBlank
    @Size(min = 1, max = 20)
    private String password;

}
