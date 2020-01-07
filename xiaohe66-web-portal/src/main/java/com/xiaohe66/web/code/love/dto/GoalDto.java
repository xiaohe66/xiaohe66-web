package com.xiaohe66.web.code.love.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xiaohe66.web.base.base.BaseDtoHaveCreate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.07 12:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoalDto extends BaseDtoHaveCreate {

    private String title;

    @TableField("`desc`")
    private String desc;

    private Integer money;

    private Boolean isFinish;

}
