package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author xiaohe
 * @time 2021.02.19 11:42
 */
@EqualsAndHashCode(callSuper = true)
@TableName("love_note_bak")
@Data
public class NoteBak extends BasePo {

    private Integer createId;

    private Date createTime;

    private Integer noteId;

    private String title;

    @TableField("`desc`")
    private String desc;

}
