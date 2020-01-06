package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author xiaohe
 * @time 2020.01.06 16:48
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("xiaohe66_web_love_lover_link")
@Data
public class LoverLink extends BasePo {

    private Integer loverId;
    private Integer userId;

    public LoverLink(Integer loverId, Integer userId) {
        this.loverId = loverId;
        this.userId = userId;
    }
}
