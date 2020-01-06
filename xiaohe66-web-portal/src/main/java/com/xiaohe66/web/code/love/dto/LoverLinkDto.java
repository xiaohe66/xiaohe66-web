package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2020.01.06 18:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoverLinkDto extends BaseDto {

    private String loverUserName;
    private Integer loveImgFileId;

}
