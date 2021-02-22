package com.xiaohe66.web.code.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019-10-21 11:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("xiaohe66_web_security_user_role")
public class UserRole extends BasePo {

    private Integer userId;
    private Integer roleId;

}
