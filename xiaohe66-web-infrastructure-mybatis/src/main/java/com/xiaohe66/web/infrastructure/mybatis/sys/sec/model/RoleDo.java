package com.xiaohe66.web.infrastructure.mybatis.sys.sec.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.IDo;
import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.11.01 16:51
 */
@TableName("sys_role")
@Data
public class RoleDo implements IDo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String roleName;
    private String roleDesc;
    private Boolean defaultRole;

}
