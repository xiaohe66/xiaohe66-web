package com.xiaohe66.web.code.sys.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2021.02.22 17:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuDto extends BaseDto {

    private Integer pid;
    private String name;
    private String link;
}
