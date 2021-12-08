package com.xiaohe66.web.infrastructure.mybatis.account.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.08.12 10:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("account")
public class AccountDo extends BaseLongInputDo {

    private String name;
    private String password;
    
}
