package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.06 16:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoverDto extends BaseDto {

    private Integer createId;
    private String createTime;
    private Integer status;

    private LoverLinkDto loverInfo;

}
