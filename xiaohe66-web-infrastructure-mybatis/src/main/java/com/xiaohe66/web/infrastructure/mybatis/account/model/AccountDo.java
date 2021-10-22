package com.xiaohe66.web.infrastructure.mybatis.account.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.08.12 10:15
 */
@Data
@TableName("account")
public class AccountDo {

    private Long id;
    private String name;
}
