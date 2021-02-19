package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2021.02.19 10:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NoteDto extends BaseDto {

    private Integer createId;

    private String updateTime;

    private String title;

    private String desc;

}
