package com.xiaohe66.web.code.org.po;

import com.xiaohe66.web.base.base.BasePoDetailed;
import com.xiaohe66.web.base.enums.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.12.06 16:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxUser extends BasePoDetailed {

    private Integer userId;
    private String openId;
    private String unionId;
    private String nickname;
    private String phone;
    private SexEnum sex;
    private String province;
    private String city;
    private String country;
    private String avatarUrl;
}
