package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.22 11:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WishDto extends BaseDto {

    private String name;
    private String desc;
    private List<WishLogDto> wishLogList;

}
