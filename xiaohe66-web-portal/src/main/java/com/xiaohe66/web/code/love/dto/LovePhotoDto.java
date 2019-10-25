package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.13 17:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LovePhotoDto extends BaseDto {

    private String name;

    private String one;
    private String two;
    private String three;

}
