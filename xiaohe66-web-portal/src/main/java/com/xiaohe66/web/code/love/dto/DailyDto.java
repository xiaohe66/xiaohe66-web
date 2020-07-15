package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.12.06 16:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DailyDto extends BaseDto {

    private String createTime;
    private String desc;
    private Integer mood;
}
