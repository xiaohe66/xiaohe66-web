package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.10 12:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MoneyDto extends BaseDto {

    private Integer balance;
}
