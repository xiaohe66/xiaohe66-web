package com.xiaohe66.web.infrastructure.mybatis.love.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小日常
 *
 * @author xiaohe66
 * @since 2019.12.06 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("love_daily")
public class DailyDo extends BaseLongInputDo {

    private Long loverId;

    @TableField("`desc`")
    private String desc;

    private Integer mood;

}
