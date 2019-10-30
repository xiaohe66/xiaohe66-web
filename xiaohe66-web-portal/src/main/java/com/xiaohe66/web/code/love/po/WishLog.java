package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author xiaohe
 * @time 2019.10.30 17:04
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_love_wish")
@Data
public class WishLog extends BasePoDetailed {

    private Integer wishId;
    private Date recordTime;
    private String message;

}
