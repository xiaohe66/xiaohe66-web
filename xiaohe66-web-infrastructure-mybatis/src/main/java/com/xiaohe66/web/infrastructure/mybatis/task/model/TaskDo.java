package com.xiaohe66.web.infrastructure.mybatis.task.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author xiaohe
 * @since 2021.11.17 11:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("task")
public class TaskDo extends BaseLongInputDo {

    private Integer poolId;

    private LocalDateTime changeTime;

    @TableField("`name`")
    private String name;

    private String remark;
    private Integer sort;

}
