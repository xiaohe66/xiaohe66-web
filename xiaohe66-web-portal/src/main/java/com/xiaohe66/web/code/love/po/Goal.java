package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.07 12:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("xiaohe66_web_love_goal")
public class Goal extends LovePoDetailed {

    private String title;

    @TableField("`desc`")
    private String desc;

    private Integer money;

    private Boolean isFinish;

}
