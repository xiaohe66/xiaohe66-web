package com.xiaohe66.web.code.security.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("xiaohe66_web_security_permission")
public class Permission extends BasePoDetailed {

    @TableField("`name`")
    private String name;

    @TableField("`desc`")
    private String desc;

}
