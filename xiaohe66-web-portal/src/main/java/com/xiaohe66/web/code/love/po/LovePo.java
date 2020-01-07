package com.xiaohe66.web.code.love.po;

import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.07 12:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LovePo extends BasePo {

    private Integer loverId;
}
