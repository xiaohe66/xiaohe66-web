package com.xiaohe66.web.infrastructure.mybatis.wx.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.10.29 17:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("wx_user")
public class WxUserDo extends BaseLongInputDo {

    private String unionId;
    private String nickname;

    private Long sex;
    private String province;
    private String city;
    private String country;

    private String sessionKey;
}
