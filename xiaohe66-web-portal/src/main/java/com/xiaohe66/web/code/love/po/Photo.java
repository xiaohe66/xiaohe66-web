package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.11 09:46
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_love_photo")
@Data
public class Photo extends BasePoDetailed {

    private Integer fileId;
    private String name;
    private String one;
    private String two;
    private String three;
    private Boolean isShow;
    private Integer sort;
}
