package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.11 18:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PhotoDto extends BaseDtoDetailed {

    private Integer fileId;
    private String name;

    private String one;
    private String two;
    private String three;

    private Boolean isShow;
}
