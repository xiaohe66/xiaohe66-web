package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2021.02.19 10:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("love_note")
public class Note extends LovePoDetailed {

    private String title;

    @TableField("`desc`")
    private String desc;

    private Integer isTop;

}
