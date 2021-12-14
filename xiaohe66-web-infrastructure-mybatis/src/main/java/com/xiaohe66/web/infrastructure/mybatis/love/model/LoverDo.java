package com.xiaohe66.web.infrastructure.mybatis.love.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.11.29 12:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("love_lover")
public class LoverDo extends BaseLongInputDo {

    private Long accountId;
    private Integer status;

}
