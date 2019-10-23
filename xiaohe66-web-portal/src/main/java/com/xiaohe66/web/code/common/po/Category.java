package com.xiaohe66.web.code.common.po;


import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_comm_category")
@Data
public class Category extends BasePoDetailed {

    private String categoryName;
    private String categoryDesc;
    private String pid;
}
