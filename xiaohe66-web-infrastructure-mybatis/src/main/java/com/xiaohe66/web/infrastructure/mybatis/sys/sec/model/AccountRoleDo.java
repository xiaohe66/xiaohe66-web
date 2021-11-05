package com.xiaohe66.web.infrastructure.mybatis.sys.sec.model;

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

    private Long accountId;
    private Integer roleId;

}
