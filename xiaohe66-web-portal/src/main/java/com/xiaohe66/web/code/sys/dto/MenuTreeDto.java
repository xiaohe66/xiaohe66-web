package com.xiaohe66.web.code.sys.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xiaohe
 * @time 2021.02.23 10:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTreeDto extends BaseDto {

    private String name;
    private String link;

    private List<MenuTreeDto> children;
}
