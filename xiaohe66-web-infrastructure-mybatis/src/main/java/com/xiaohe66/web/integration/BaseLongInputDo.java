package com.xiaohe66.web.integration;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.xiaohe66.common.web.mybatis.base.BaseEntityInputId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author xiaohe
 * @since 2021.11.01 10:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseLongInputDo extends BaseEntityInputId implements IDo {

    private Long createId;

    private LocalDateTime updateTime;

    @TableLogic
    private Boolean deleted;

}
