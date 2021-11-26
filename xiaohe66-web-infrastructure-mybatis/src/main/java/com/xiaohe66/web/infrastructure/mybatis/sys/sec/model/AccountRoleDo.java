package com.xiaohe66.web.infrastructure.mybatis.sys.sec.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.10.28 18:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_account_role")
public class AccountRoleDo extends BaseLongInputDo {

    private Long accountId;
    private Integer roleId;

}
