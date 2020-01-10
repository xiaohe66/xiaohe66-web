package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.10 12:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("xiaohe66_web_love_money")
public class Money extends BasePoDetailed {

    private Integer balance;

}
