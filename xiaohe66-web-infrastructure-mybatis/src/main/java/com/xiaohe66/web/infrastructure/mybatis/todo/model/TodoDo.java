package com.xiaohe66.web.infrastructure.mybatis.todo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.11.17 11:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("todo")
public class TodoDo extends BaseLongInputDo {

    private Long createId;

    @TableLogic
    private Boolean deleted;

    private Integer poolId;

    @TableField("`name`")
    private String name;

    private String remark;
    private Integer sort;

}
