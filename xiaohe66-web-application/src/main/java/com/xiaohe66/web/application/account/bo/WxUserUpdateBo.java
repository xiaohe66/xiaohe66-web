package com.xiaohe66.web.application.account.bo;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.12.07 12:15
 */
@Data
public class WxUserUpdateBo {

    private String nickname;
    private String avatarUrl;

    private Long sex;
    private String province;
    private String city;
    private String country;

}
