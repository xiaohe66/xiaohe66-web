package com.xiaohe66.web.code.sys.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2021.02.22 17:45
 */
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = true)
@Data
public class Menu extends BasePoDetailed {

    private Integer pid;

    @TableField("`name`")
    private String name;

    private String link;

}
