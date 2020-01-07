package com.xiaohe66.web.base.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.07 12:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseDtoHaveCreate extends BaseDto{

    protected Integer createId;
    protected String createTime;

}
