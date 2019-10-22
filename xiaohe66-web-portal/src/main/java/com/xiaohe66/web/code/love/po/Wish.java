package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.11 10:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("xiaohe66_web_love_wish")
public class Wish extends BasePoDetailed {

    @TableField("`name`")
    private String name;
    private String email;
    private String message;

}
