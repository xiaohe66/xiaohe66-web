package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.30 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlessDto extends BaseDto {

    private String name;
    private String message;

}
