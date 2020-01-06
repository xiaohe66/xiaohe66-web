package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.06 16:41
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_love_lover")
@Data
public class Lover extends BasePoDetailed {

    private Integer status;

}
