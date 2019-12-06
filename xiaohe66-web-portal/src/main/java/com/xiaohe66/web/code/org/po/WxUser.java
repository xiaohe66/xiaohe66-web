package com.xiaohe66.web.code.org.po;

import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.12.06 16:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxUser extends BasePoDetailed {

    private String openId;
    private String nickname;
    private String token;

}
