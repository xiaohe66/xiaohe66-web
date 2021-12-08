package com.xiaohe66.web.infrastructure.mybatis.sys.sec.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.IDo;
import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.10.28 18:12
 */
@Data
@TableName("sys_account_role")
public class AccountRoleDo implements IDo {

    @TableId(type = IdType.INPUT)
    private Long id;

    private Long accountId;

    private Integer roleId;

}
