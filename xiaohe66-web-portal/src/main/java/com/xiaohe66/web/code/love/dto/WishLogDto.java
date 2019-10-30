package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.30 17:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WishLogDto extends BaseDto {

    private String recordTime;
    private String message;
}
