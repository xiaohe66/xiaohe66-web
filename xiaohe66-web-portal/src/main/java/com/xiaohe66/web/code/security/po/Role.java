package com.xiaohe66.web.code.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_security_role")
@Data
public class Role extends BasePoDetailed {

    private String roleName;
    private String roleDesc;
    private Boolean isDefault;
}
